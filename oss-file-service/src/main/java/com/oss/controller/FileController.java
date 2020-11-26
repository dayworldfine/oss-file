package com.oss.controller;

import com.github.pagehelper.PageInfo;
import com.oss.pojo.bo.FileBo;
import com.oss.pojo.dto.FileListDto;
import com.oss.service.FileService;
import com.oss.service.LogService;
import com.oss.service.ZoneService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseModel;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.oss.tool.BaseController;
import com.oss.service.UploadService;
import com.oss.tool.util.ValidateUtil;

import javax.validation.Valid;

/**
 * @ClassName：fileController
 * @Description: 文件操作控制层
 * @Author：13738700108
 * @Data 2020/10/18 18:37
 * @Version: v1.0
 **/
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private LogService logService;


    /**
     * @api {POST} /changeSubject 查看文件列表
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
    @PostMapping("/getFileList")
    public ResponseModel getFileList(@Valid FileListDto fileListDto)  {
        ResponseResult<PageInfo<FileBo>> responseResult =  fileService.pageFileByZoneId(fileListDto);
        return responseResult.isSuccess()?ResponseModel.success(responseResult.getData()):ResponseModel.error(ErrorCodes.SERVER_INTERNAL_ERROR);
    }


    /**
     * @api {POST} /uploadFile 上传文件
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
    @PostMapping("/uploadFile")
    public ResponseModel uploadFile(MultipartFile file,String zoneId) {
        try {
//            long userId = ShiroUtil.getUserId();
            long userId = 1l;
            if (ValidateUtil.isEmpty(file) || file.isEmpty()) {
                return ResponseModel.error(ErrorCodes.FILE_NULL);
            }
            //查询分区前缀
            ResponseResult responseResult = zoneService.findPrefixById(zoneId);
            if (responseResult.isSuccess()){
                //查询数据库文件名是否已经存在
                ResponseResult findResult =  fileService.findFileByNameAndZoneId(file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf(".")),zoneId);
                if (findResult.isError()){
                    return ResponseModel.error(findResult.getErrorCode());
                }
                String prefix = responseResult.getData().toString();
                //上传文件
                ResponseResult result = uploadService.uploadFile(file,prefix);
                if (result.isError()){
                    return ResponseModel.error(result.getErrorCode());
                }
                //添加数据库数据
                ResponseResult addResult = fileService.addFile(file,prefix,zoneId,userId);
                if (addResult.isError()){
                    return ResponseModel.error(addResult.getErrorCode());
                }
                //添加日志
                String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
                ResponseResult logResult = logService.addFileLog(fileName,Long.valueOf(addResult.getData().toString()), EnumUtil.File_OPERATION_ENUM.UPLOAD.getValue(),Long.valueOf(zoneId),userId);
                if (logResult.isError()){
                    return ResponseModel.error(addResult.getErrorCode());
                }
                return ResponseModel.OK();
            }else {
                return ResponseModel.error(responseResult.getErrorCode());
            }
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return ResponseModel.error(ErrorCodes.FILE_SUFFIX);
        }
    }

    /**
     * @api {POST} / 删除文件
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
    @PostMapping("/delPrefixFile")
    public ResponseModel delPrefixFile(String prefix) {
        if (ValidateUtil.isEmpty(prefix)) {
            return ResponseModel.error(ErrorCodes.PARAM_EMPTY_ERROR);
        }
        //上传文件
       ResponseResult responseResult =  uploadService.delPrefixFile(prefix);
        return responseResult.isSuccess()?ResponseModel.OK():ResponseModel.error(responseResult.getErrorCode());
    }

    /**
     * @api {POST} /changeSubject 下载文件
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
    public ResponseModel downloadFile(String fileId) {
        if (ValidateUtil.isEmpty(fileId)) {
            return ResponseModel.error(ErrorCodes.PARAM_EMPTY_ERROR);
        }
        //上传文件
        ResponseResult responseResult =  uploadService.delPrefixFile(fileId);
        return responseResult.isSuccess()?ResponseModel.OK():ResponseModel.error(responseResult.getErrorCode());
    }


}
