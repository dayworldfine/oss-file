package com.oss.service;

import com.github.pagehelper.PageInfo;
import com.oss.model.File;
import com.oss.pojo.bo.FileBo;
import com.oss.pojo.dto.FileListDto;
import com.oss.tool.BaseService;
import com.oss.tool.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件
 */
public interface FileService  extends BaseService {


    /**
     * 分页查询
     */
    ResponseResult<PageInfo<FileBo>> pageFileByZoneId(FileListDto fileListDto);


    /**
     * 根据名字和分区id搜索文件
     * @param substring
     * @param zoneId
     * @return
     */
    ResponseResult findFileByNameAndZoneId(String substring, String zoneId);

    /**
     * 上传文件
     * @param file
     * @param prefix
     */
    ResponseResult uploadFile(MultipartFile file, String prefix);

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

    /**
     * 删除文件
     * @param fileId
     * @return
     */
    ResponseResult delFileById(String fileId);


    /**
     * 根据文件id查询文件
     * @param fileId
     * @return
     */
    File selectFileByFileId(String fileId);
}
