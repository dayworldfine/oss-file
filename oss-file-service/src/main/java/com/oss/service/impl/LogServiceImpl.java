package com.oss.service.impl;

import com.oss.mapper.FileLogMapper;
import com.oss.model.FileLog;
import com.oss.service.LogService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.SnowUtil;
import com.oss.tool.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName：LogServiceImpl
 * @Description: 日志实现
 * @Author：13738700108
 * @Data 2020/11/1 22:24
 * @Version: v1.0
 **/
@Transactional
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private FileLogMapper fileLogMapper;

    @Override
    public ResponseResult addFileLog(String fileName,Long fileId, int type, Long zoneId, long userId) {
        FileLog fileLog = new FileLog();
        fileLog.setId(SnowUtil.generateId());
        fileLog.setCreateTime(System.currentTimeMillis());
        fileLog.setUpdateTime(System.currentTimeMillis());
        fileLog.setVersion(1l);
        fileLog.setFileId(fileId);
        fileLog.setFileName(fileName);
        fileLog.setOperationType(type);
        fileLog.setZoneId(zoneId);
        fileLog.setUserId(userId);
        int insert = fileLogMapper.insert(fileLog);
        return ValidateUtil.isNotCountEmpty(insert)?ResponseResult.responseSuccessResult(insert):ResponseResult.responseResultWithErrorCode(ErrorCodes.ADD_ERROR);
    }
}
