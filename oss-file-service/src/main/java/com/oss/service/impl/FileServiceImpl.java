package com.oss.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oss.mapper.FileMapper;
import com.oss.model.File;
import com.oss.pojo.bo.FileBo;
import com.oss.service.FileService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.SnowUtil;
import com.oss.tool.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@Transactional
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;


    @Override
    public ResponseResult pageFileByZoneId(String zoneId, Integer page, Integer size) {
        PageHelper.startPage(page,size);

        Page<FileBo> pageInfo = fileMapper.pageFileByZoneId(zoneId);
        return ResponseResult.responseSuccessResult(pageInfo);
    }

    @Override
    public ResponseResult findFileByNameAndZoneId(String fileName, String zoneId) {
        File file =  fileMapper.findFileByNameAndZoneId(fileName,zoneId);
        return ValidateUtil.isEmpty(file)?ResponseResult.responseOK():ResponseResult.responseResultWithErrorCode(ErrorCodes.FILE_NAME_HAVE);
    }

    @Override
    public ResponseResult addFile(MultipartFile file, String prefix, String zoneId, long userId) {
        String filename = file.getOriginalFilename();
        File file1 = new File();
        Long id = SnowUtil.generateId();
        file1.setId(id);
        file1.setCreateTime(System.currentTimeMillis());
        file1.setUpdateTime(System.currentTimeMillis());
        file1.setVersion(1l);
        file1.setFileName(filename.substring(0,filename.lastIndexOf(".")));
        file1.setSuffix(filename.substring(filename.lastIndexOf(".")));
        file1.setUrl(prefix+filename);
        file1.setUploadUserId(userId);
        file1.setDownloadStatistics(0l);
        file1.setPreviewStatistics(0l);
        file1.setZoneId(Long.valueOf(zoneId));
        int insert = fileMapper.insert(file1);

        return ValidateUtil.isNotCountEmpty(insert)?ResponseResult.responseSuccessResult(id):ResponseResult.responseResultWithErrorCode(ErrorCodes.ADD_ERROR);
    }

    @Override
    public ResponseResult addFileStatistics(int type, String fileId) {
        Integer num = fileMapper.addFileStatistics(type,fileId);
        return ValidateUtil.isNotEmpty(num)?ResponseResult.responseOK():ResponseResult.responseResultWithErrorCode(ErrorCodes.ADD_FILE_STATISTICS_ERROR);
    }


}
