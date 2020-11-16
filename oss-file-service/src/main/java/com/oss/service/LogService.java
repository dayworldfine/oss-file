package com.oss.service;

import com.oss.config.BaseService;
import com.oss.tool.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @InterfaceName：LogService
 * @Description: 日志
 * @Author：13738700108
 * @Data 2020/11/1 22:24
 * @Version: v1.0
 **/
public interface LogService extends BaseService {

    /**
     * 添加文件日志
     * @param fileName
     * @param fileId
     * @param type
     * @param zoneId
     * @param userId
     * @return
     */
    ResponseResult addFileLog(String fileName,Long fileId, int type, Long zoneId, long userId);
}
