package com.oss.controller;


import com.oss.tool.BaseController;
import com.oss.pojo.bo.DocumentPreviewBo;
import com.oss.pojo.bo.DocumentRefreshBo;
import com.oss.pojo.dto.DocumentSignDto;
import com.oss.pojo.vo.ImmDocumentRefreshVo;
import com.oss.pojo.vo.ImmDocumentVo;
import com.oss.service.DocumentService;
import com.oss.service.FileService;
import com.oss.service.LogService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseModel;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.EnumUtil;
import com.oss.tool.util.JsonUtil;
import com.oss.tool.util.ValidateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName：DocumentController
 * @Description: 文档控制层
 * @Author：13738700108
 * @Data 2020/10/19 22:30
 * @Version: v1.0
 **/
@RestController
@RequestMapping("/document")
public class DocumentController extends BaseController {

    @Autowired
    private LogService logService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FileService fileService;

    /**
     * @api {POST} /changeSubject 获取文档预览地址
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
    @PostMapping("/getDocumentUrl")
    public ResponseModel getDocumentUrl(String  fileId)  {
//            long userId = ShiroUtil.getUserId();
        long userId = 1l;
        if (ValidateUtil.isEmpty(fileId)){
            return ResponseModel.error(ErrorCodes.PARAM_EMPTY_ERROR);
        }
        /** 获取地址*/
        ResponseResult responseResult = documentService.getDocumentUrl(fileId);
        if (responseResult.isError()){
            return ResponseModel.error(responseResult.getErrorCode());
        }
        DocumentPreviewBo documentPreviewBo = JsonUtil.toBean(JsonUtil.toJson(responseResult.getData()), DocumentPreviewBo.class);
        /** 添加日志*/
        ResponseResult addFileLog = logService.addFileLog(documentPreviewBo.getFileName(), Long.valueOf(fileId), EnumUtil.File_OPERATION_ENUM.PREVIEW.getValue(), Long.valueOf(documentPreviewBo.getZoneId()), userId);
        if (addFileLog.isError()){
            return ResponseModel.error(responseResult.getErrorCode());
        }
        /** 更改获取文档记录+1*/
        ResponseResult addFilePreviewStatistics = fileService.addFileStatistics(1,fileId);

        /** 拼接数据*/
        ImmDocumentVo immDocumentVo = new ImmDocumentVo();
        BeanUtils.copyProperties(documentPreviewBo,immDocumentVo);

        return ResponseModel.success(immDocumentVo);
    }


    /**
     * @api {POST} /changeSubject 刷新凭证
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
    @PostMapping("/refreshSign")
    public ResponseModel refreshSign(@Valid  DocumentSignDto documentSignDto, BindingResult bindingResult)  {
//      long userId = ShiroUtil.getUserId();
        long userId = 1l;
        if (bindingResult.hasErrors()){
            return ResponseModel.errorWithMsg(ErrorCodes.PARAM_VALID_ERROR,bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

       ResponseResult responseResult =  documentService.refreshSign(documentSignDto);
        if (responseResult.isError()){
            return ResponseModel.error(responseResult.getErrorCode());
        }
        DocumentRefreshBo documentPreviewBo = JsonUtil.toBean(JsonUtil.toJson(responseResult.getData()), DocumentRefreshBo.class);
        ResponseResult addFileLog = logService.addFileLog(documentPreviewBo.getFileName(), Long.valueOf(documentSignDto.getFileId()), EnumUtil.File_OPERATION_ENUM.SIGN.getValue(), Long.valueOf(documentPreviewBo.getZoneId()), userId);
        if (addFileLog.isError()){
            return ResponseModel.error(responseResult.getErrorCode());
        }
        ImmDocumentRefreshVo immDocumentRefreshVo = new ImmDocumentRefreshVo();
        BeanUtils.copyProperties(documentPreviewBo,immDocumentRefreshVo);

        return ResponseModel.success(immDocumentRefreshVo);
    }


}
