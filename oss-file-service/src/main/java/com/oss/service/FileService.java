package com.oss.service;

import com.oss.tool.BaseService;
import com.oss.tool.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件
 */
public interface FileService  extends BaseService {


    /**
     * 分页查询
     * @param zoneId
     * @param page
     * @param size
     */
    ResponseResult pageFileByZoneId(String zoneId, Integer page, Integer size);


    /**
     * 根据名字和分区id搜索文件
     * @param substring
     * @param zoneId
     * @return
     */
    ResponseResult findFileByNameAndZoneId(String substring, String zoneId);

    /**
     * 添加文件
     * @param file
     * @param prefix
     * @param zoneId
     * @param userId
     * @return
     */
    ResponseResult addFile(MultipartFile file, String prefix, String zoneId, long userId);

    /**
     * 添加文件预览或下载次数
     * @param type 0 下载 1 预览
     * @param fileId
     * @return
     */
    ResponseResult addFileStatistics(int type, String fileId);
}
