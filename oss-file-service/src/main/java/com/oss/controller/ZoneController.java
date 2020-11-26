package com.oss.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.oss.model.Zone;
import com.oss.pojo.bo.ZonePwdBo;
import com.oss.tool.BaseController;
import com.oss.pojo.bo.ZoneBo;
import com.oss.pojo.dto.ZoneDto;
import com.oss.pojo.dto.ZoneListDto;
import com.oss.service.UserService;
import com.oss.service.ZoneService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseModel;
import com.oss.tool.ResponseResult;
import com.oss.tool.shiro.ShiroHandler;
import com.oss.tool.util.SnowUtil;
import com.oss.tool.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


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
    public ResponseModel getZoneList(@Valid ZoneListDto zoneListDto)  {
        ResponseResult<PageInfo<ZoneBo>> zoneBoPage = zoneService.pageZoneByUserId(ShiroHandler.getUserId(),zoneListDto);
        return ResponseModel.success(zoneBoPage.getData());
    }

    /**
     * 分配分区查询分区列表
     * @return
     */
    @PostMapping("/queryZoneByParam")
    public ResponseModel queryZoneByParam(@Valid ZoneListDto zoneListDto)  {
        ResponseResult<Page<Zone>> zoneBoPage = zoneService.pageQueryZoneByUserId(zoneListDto);
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
    public ResponseModel addZone( @Valid ZoneDto zoneDto)  {
        //无需添加oss上的路径

        //添加数据库的数据
        ResponseResult responseResult = zoneService.addZone(zoneDto);
        return responseResult.isSuccess()?ResponseModel.OK():ResponseModel.error(responseResult.getErrorCode());
    }


    /**
     * 根据分区id删除分区
     * @param zoneId
     * @return
     */
    @PostMapping("/deleteZoneById")
    public ResponseModel deleteZoneById(String zoneId)  {
        //删除oss上的路径 删除数据库的数据
        ResponseResult zoneBoPage = zoneService.deleteZoneById(zoneId);
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
        ResponseResult<List<ZonePwdBo>> responseResult = zoneService.getMyZonePwd(ShiroHandler.getUserId());
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

        ResponseResult responseResult = userService.getZoneKey(ShiroHandler.getUserId(),pwd);
        return responseResult.isSuccess()?ResponseModel.success():ResponseModel.error(responseResult.getErrorCode());
    }


}
