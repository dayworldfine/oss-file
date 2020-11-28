package com.oss.controller;

import com.github.pagehelper.Page;
import com.oss.pojo.dto.UserSelectKeyDto;
import com.oss.pojo.vo.UserVo;
import com.oss.service.UserService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseModel;
import com.oss.tool.ResponseResult;
import com.oss.tool.shiro.ShiroHandler;
import com.oss.tool.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @ClassName：UserController
 * @Description: 用户控制层
 * @Author：13738700108
 * @Data 2020/11/2 0:08
 * @Version: v1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @api {POST} /changeSubject 条件查询用户
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
    @PostMapping("/queryUserByParam")
    public ResponseModel queryUserByParam(@Valid UserSelectKeyDto userSelectKeyDto)  {
        ResponseResult<Page<UserVo>> responseResult = userService.pageUserBySelectKey(userSelectKeyDto);

        return responseResult.isSuccess()?ResponseModel.success(responseResult.getData()):ResponseModel.error(responseResult.getErrorCode());
    }

    /**
     * @api {POST} /changeSubject 修改用户昵称
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
    @PostMapping("/updateUserName")
    public ResponseModel updateUserName(String userName)  {
        if (ValidateUtil.isEmpty(userName)){
            return ResponseModel.error(ErrorCodes.PARAM_EMPTY_ERROR);
        }
        ResponseResult responseResult = userService.updateUserName(userName, ShiroHandler.getUserId());

        return responseResult.isSuccess()?ResponseModel.success(responseResult.getData()):ResponseModel.error(responseResult.getErrorCode());
    }

    /**
     * @api {POST} /changeSubject 修改用户头像
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
    @PostMapping("/updateUserImg")
    public ResponseModel updateUserImg( MultipartFile file)  {
        if (ValidateUtil.isEmpty(file)){
            return ResponseModel.error(ErrorCodes.PARAM_EMPTY_ERROR);
        }
        ResponseResult responseResult = userService.updateUserImg(file, ShiroHandler.getUserId());

        return responseResult.isSuccess()?ResponseModel.success(responseResult.getData()):ResponseModel.error(responseResult.getErrorCode());
    }


}
