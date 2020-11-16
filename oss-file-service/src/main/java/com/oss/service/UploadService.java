package com.oss.service;

import com.oss.tool.ResponseResult;
import org.springframework.web.multipart.MultipartFile;
import com.oss.config.BaseService;

public interface UploadService extends BaseService {

    /**
     * 上传文件
     * @param file
     * @param prefix
     */
    ResponseResult uploadFile(MultipartFile file, String prefix);


    /**
     * 删除指定前缀文件
     * @param prefix
     */
    ResponseResult delPrefixFile(String prefix);
}
