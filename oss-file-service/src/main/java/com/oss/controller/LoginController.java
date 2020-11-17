package com.oss.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.oss.config.BaseController;
import com.oss.model.User;
import com.oss.tool.ResponseModel;
import com.oss.tool.redis.RedisService;
import com.oss.tool.util.SmsUtil;
import com.oss.tool.util.ValidateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName：fileController
 * @Description: 登录
 * @Author：13738700108
 * @Data 2020/10/18 18:37
 * @Version: v1.0
 **/
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private RedisService redisService;


    @PostMapping("/sendSms")
    public ResponseModel sendSms(String account){
        Subject subject = SecurityUtils.getSubject();

        DefaultProfile profile = DefaultProfile.getProfile(smsUtil.getRegionId(), smsUtil.getAccessKeyId(), smsUtil.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", smsUtil.getRegionId());
        request.putQueryParameter("PhoneNumbers", account);
        request.putQueryParameter("SignName", smsUtil.getSignName());
        request.putQueryParameter("TemplateCode", smsUtil.getTemplateCode());
        String code = SmsUtil.generalRandomVeriCode();
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        redisService.set(account,code,3600l);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
            return ResponseModel.error(-10000);
        } catch (ClientException e) {
            e.printStackTrace();
            return ResponseModel.error(-10000);
        }
        //存入本地缓存

        return ResponseModel.success("发送成功");
    }

    /**
     * @api {POST} /changeSubject 注册
     * @apiGroup 接口小组
     * @apiVersion 1.0.0
     * @apiDescription 接口说明
     * @apiParam {String} id 机构ID
     * @apiParamExample {json} 请求样例：
     *                ?id=bfc5bd62010f467cbbe98c9e4741733b
     * @apiSuccess (200) {String} code 200:成功</br>
     *                                 404:机构不存在/ID为空</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     * 	"code": 200,
     * 	"message": "登录成功",
     * 	"data": "{}"
     * }
     */
    @PostMapping("/register")
    public ResponseModel register(String userName,
                               String nickName,
                               String code,
                               String pwd,
                               String account){
        if (!redisService.get(account).toString().equals(code)){
            return  ResponseModel.error(-10000);
        }
//        User userByUserName = userService.findUserByUserName(userName);
//        if (ValidateUtil.isNotEmpty(userByUserName)){
//            return ResponseModel.error(-10000);
//        }
//        userService.addUser(userName,nickName,pwd,account);
        return ResponseModel.success("注册成功 请使用新账号登录");
    }

    /**
     * @api {POST} /changeSubject 登录
     * @apiGroup 接口小组
     * @apiVersion 1.0.0
     * @apiDescription 接口说明
     * @apiParam {String} id 机构ID
     * @apiParamExample {json} 请求样例：
     *                ?id=bfc5bd62010f467cbbe98c9e4741733b
     * @apiSuccess (200) {String} code 200:成功</br>
     *                                 404:机构不存在/ID为空</br>
     * @apiSuccess (200) {String} message 信息
     * @apiSuccess (200) {String} data 返回用户信息
     * @apiSuccessExample {json} 返回样例:
     * {
     * 	"code": 200,
     * 	"message": "登录成功",
     * 	"data": "{}"
     * }
     */
    @PostMapping("/login")
    public ResponseModel login(String userName,
                            String passWord){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,passWord);
        try {
            subject.login(usernamePasswordToken);
            System.out.println("token:"+subject.getSession().getId());
            User user = (User) subject.getPrincipals().getPrimaryPrincipal();
            JSONObject object = new JSONObject();
            object.put("token",subject.getSession().getId());
            object.put("userId",String.valueOf(user.getId()));
            return ResponseModel.success(object);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseModel.error(-10000);
        }
    }
}
