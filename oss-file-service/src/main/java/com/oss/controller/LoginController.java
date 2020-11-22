package com.oss.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.oss.pojo.vo.LoginVo;
import com.oss.tool.BaseController;
import com.oss.model.User;
import com.oss.pojo.dto.RegisterDto;
import com.oss.service.UserService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseModel;
import com.oss.tool.ResponseResult;
import com.oss.tool.redis.RedisService;
import com.oss.tool.util.RedisUtil;
import com.oss.tool.util.SmsUtil;
import com.oss.tool.util.ValidateUtil;
import com.oss.tool.util.VerificationUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    private RedisUtil redisUtil;

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;


    @PostMapping("/sendSms")
    public ResponseModel sendSms(String account){
        if (!VerificationUtil.checkPhone(account)){
          return   ResponseModel.error(ErrorCodes.PHONE_ERROR);
        }

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
        redisUtil.setex(account, code, 180,15);
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
    public ResponseModel register(@Valid RegisterDto registerDto){
        if (!registerDto.getCode().equals(redisUtil.get(registerDto.getAccount(),15))){
            return  ResponseModel.error(ErrorCodes.CODE_ERROR);
        }
        ResponseResult<User> userResponseResult = userService.findUserByAccount(registerDto.getAccount());
        if (ValidateUtil.isNotEmpty(userResponseResult.getData())){
            return ResponseModel.error(ErrorCodes.PHONE_IS_HAVE);
        }
        ResponseResult responseResult = userService.addUser(registerDto);
        return responseResult.isSuccess()?ResponseModel.OK():ResponseModel.error(responseResult.getErrorCode());
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
    public ResponseModel login(String account,
                            String passWord){
        if (ValidateUtil.isEmpty(account) || ValidateUtil.isEmpty(passWord)){
            return ResponseModel.error(ErrorCodes.PARAM_VALID_ERROR);
        }
        Subject     subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(account,passWord,true);
        try {
            subject.login(usernamePasswordToken);
            System.out.println("token:"+subject.getSession().getId());
            User user = (User) subject.getPrincipals().getPrimaryPrincipal();
            LoginVo loginVo = new LoginVo();
            loginVo.setToken(String.valueOf(subject.getSession().getId()));
            loginVo.setUser(user);
            return ResponseModel.success(loginVo);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseModel.error(-10000);
        }
    }

    @PostMapping("/textRedis")
    public ResponseModel textRedis(){
        String setex = redisUtil.setex("13900000000", "666666", 60,15);
//        String set = redisUtil.set("13900000000", "666666", 15);
        String s = redisUtil.get("13900000000", 15);
        System.out.println("s:"+s);

//        redisService.set("13900000000","666666",60l);
//        Object o = redisService.get("13900000000");
//        System.out.println("o:"+o);

        return ResponseModel.success("发送成功");
    }
}
