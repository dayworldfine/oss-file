package com.oss.controller;

import com.oss.tool.BaseController;
import com.oss.tool.ResponseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName：CipherController
 * @Description: 暗号
 * @Author：13738700108
 * @Data 2020/11/2 0:32
 * @Version: v1.0
 **/
@RestController
@RequestMapping("/cipher")
public class CipherController extends BaseController {

    /**
     * @api {POST} /changeSubject 获取角色列表
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
    public ResponseModel getRoleList(String zoneId,
                                     Integer page,
                                     Integer size)  {
        return null;
    }
}
