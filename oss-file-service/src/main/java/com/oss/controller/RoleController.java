package com.oss.controller;

import com.oss.config.BaseController;
import com.oss.pojo.dto.RoleAllotDto;
import com.oss.pojo.dto.ZoneAllotDto;
import com.oss.pojo.dto.ZoneListDto;
import com.oss.service.RoleService;
import com.oss.service.UserService;
import com.oss.service.ZoneService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseModel;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.ShiroUtil;
import com.oss.tool.util.ValidateUtil;
import net.sf.saxon.trans.Err;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @ClassName：RoleController
 * @Description: 角色
 * @Author：13738700108
 * @Data 2020/11/1 23:51
 * @Version: v1.0
 **/
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private UserService userService;


    /**
     * @api {POST} /changeSubject 查询角色列表
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
    @PostMapping("/getRoleList")
    public ResponseModel getRoleList()  {
        ResponseResult responseResult = roleService.getRoleList();
        return responseResult.isSuccess()?ResponseModel.success(responseResult.getData()):ResponseModel.error(responseResult.getErrorCode());
    }


    /**
     * @api {POST} /changeSubject 给用户分配角色
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
    @PostMapping("/allotRole")
    public ResponseModel allotRole(@RequestBody @Valid RoleAllotDto roleAllotDto, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()){
            return ResponseModel.errorWithMsg(ErrorCodes.PARAM_VALID_ERROR,bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        ResponseResult responseResult = roleService.allotRole(roleAllotDto);
        return responseResult.isSuccess()?ResponseModel.success():ResponseModel.error(responseResult.getErrorCode());
    }



    /**
     * @api {POST} /changeSubject 查询分区列表
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
    @PostMapping("/getZoneList")
    public ResponseModel getZoneList()  {
        ResponseResult responseResult =  zoneService.selectAllZoneList();

        return responseResult.isSuccess()?ResponseModel.success(responseResult.getData()):ResponseModel.error(responseResult.getErrorCode());
    }



    /**
     * @api {POST} /changeSubject 给用户分配区域
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
    @PostMapping("/allotZone")
    public ResponseModel allotZone(@Valid @RequestBody ZoneAllotDto zoneAllotDto ,BindingResult bindingResult)  {
        if (bindingResult.hasErrors()){
            return ResponseModel.errorWithMsg(ErrorCodes.PARAM_VALID_ERROR,bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        ResponseResult responseResult = roleService.allotZone(zoneAllotDto);
        return responseResult.isSuccess()?ResponseModel.success():ResponseModel.error(responseResult.getErrorCode());
    }




    /**
     * @api {POST} /changeSubject 输入角色密钥
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
    @PostMapping("/getRoleKey")
    public ResponseModel getRoleKey(String pwd)  {
        if (ValidateUtil.isEmpty(pwd)){
            return ResponseModel.error(ErrorCodes.PARAM_EMPTY_ERROR);
        }
//        long userId = ShiroUtil.getUserId();
        long userId = 1l;

        ResponseResult responseResult = userService.getRoleKey(userId,pwd);
        return responseResult.isSuccess()?ResponseModel.success():ResponseModel.error(responseResult.getErrorCode());
    }
}
