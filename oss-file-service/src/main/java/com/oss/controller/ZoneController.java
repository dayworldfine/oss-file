package com.oss.controller;

import com.github.pagehelper.Page;
import com.oss.config.BaseController;
import com.oss.mapper.ZoneMapper;
import com.oss.model.Zone;
import com.oss.pojo.bo.ZoneBo;
import com.oss.pojo.dto.ZoneDto;
import com.oss.pojo.dto.ZoneListDto;
import com.oss.service.FileService;
import com.oss.service.UserService;
import com.oss.service.ZoneService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.JsonResult;
import com.oss.tool.ResponseModel;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.ShiroUtil;
import com.oss.tool.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @ClassName：ZoneController
 * @Description: 分区
 * @Author：13738700108
 * @Data 2020/10/18 18:37
 * @Version: v1.0
 **/
@RestController
@RequestMapping("/zone")
public class ZoneController extends BaseController {

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private UserService userService;

    /**
     * 获取分区列表
     * @return
     */
    @PostMapping("/getZoneList")
    public ResponseModel getZoneList(@Valid ZoneListDto zoneListDto,BindingResult bindingResult)  {
        if (bindingResult.hasErrors()){
            return ResponseModel.errorWithMsg(ErrorCodes.PARAM_VALID_ERROR,bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
//        long userId = ShiroUtil.getUserId();
        long userId = 1l;
        ResponseResult<Page<ZoneBo>> zoneBoPage = zoneService.pageZoneByUserId(userId,zoneListDto);
        return ResponseModel.success(zoneBoPage.getData());
    }

   /**
    * @api {POST} /changeSubject 添加分区
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
    @PostMapping("/addZone")
    public ResponseModel addZone( @Valid ZoneDto zoneDto, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()){
            return ResponseModel.errorWithMsg(ErrorCodes.PARAM_VALID_ERROR,bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        ResponseResult responseResult = zoneService.addZone(zoneDto);
        return responseResult.isSuccess()?ResponseModel.OK():ResponseModel.error(responseResult.getErrorCode());
    }


    /**
     * 根据分区id删除分区
     * @param zoneIds
     * @return
     */
    @PostMapping("/deleteZoneByIds")
    public ResponseModel deleteZoneByIds(String[] zoneIds)  {
        ResponseResult<Integer> zoneBoPage = zoneService.deleteZoneByIds(zoneIds);
        return zoneBoPage.isSuccess()?ResponseModel.success(zoneBoPage.getData()):ResponseModel.error(zoneBoPage.getErrorCode());
    }


    /**
     * @api {POST} /changeSubject 查询我的分区所有密匙
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
    @PostMapping("/getMyZonePwd")
    public ResponseModel getMyZonePwd()  {
//        long userId = ShiroUtil.getUserId();
        long userId = 1l;
        ResponseResult responseResult = zoneService.getMyZonePwd(userId);
        return responseResult.isSuccess()?ResponseModel.success(responseResult.getData()):ResponseModel.error(responseResult.getErrorCode());
    }

    /**
     * @api {POST} /changeSubject 输入分区密钥
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
    @PostMapping("/getZoneKey")
    public ResponseModel getZoneKey(String pwd)  {
        if (ValidateUtil.isEmpty(pwd)){
            return ResponseModel.error(ErrorCodes.PARAM_EMPTY_ERROR);
        }
//        long userId = ShiroUtil.getUserId();
        long userId = 1l;

        ResponseResult responseResult = userService.getZoneKey(userId,pwd);
        return responseResult.isSuccess()?ResponseModel.success():ResponseModel.error(responseResult.getErrorCode());
    }


}
