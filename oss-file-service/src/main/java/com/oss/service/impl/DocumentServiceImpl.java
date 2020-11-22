package com.oss.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.imm.model.v20170906.GetOfficePreviewURLRequest;
import com.aliyuncs.imm.model.v20170906.GetOfficePreviewURLResponse;
import com.aliyuncs.imm.model.v20170906.RefreshOfficePreviewTokenRequest;
import com.aliyuncs.imm.model.v20170906.RefreshOfficePreviewTokenResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.oss.mapper.FileMapper;
import com.oss.model.File;
import com.oss.pojo.bo.DocumentPreviewBo;
import com.oss.pojo.bo.DocumentRefreshBo;
import com.oss.pojo.dto.DocumentSignDto;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.OssUtil;
import com.oss.tool.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.oss.service.DocumentService;
import com.oss.tool.util.ImmUtil;

/**
 * @ClassName：DocumentServiceImpl
 * @Description: 文档实现类
 * @Author：13738700108
 * @Data 2020/10/19 21:30
 * @Version: v1.0
 **/
@Transactional
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private ImmUtil immUtil;

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public ResponseResult getDocumentUrl(String fileId) {
        //通过fileId找到路径名称
        File file = fileMapper.selectByPrimaryKey(Long.valueOf(fileId));
        if (ValidateUtil.isEmpty(file)){
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.SELECT_NOT_FOUND);
        }
        IAcsClient client = new DefaultAcsClient(DefaultProfile.getProfile(immUtil.getEndpoint(), immUtil.getAccessKeyId(),immUtil.getAccessKeySecret()));
        GetOfficePreviewURLRequest req = new GetOfficePreviewURLRequest();
        req.setProject(immUtil.getProjectName());
        // 设置待转换对文件OSS路径
//        req.setSrcUri("oss://tom-file/ywm/新建文本文档 (4).txt");
        req.setSrcUri("oss://"+ossUtil.getBucketName()+"/"+file.getUrl());

        GetOfficePreviewURLResponse res = null;
        try {
            res = client.getAcsResponse(req);
            DocumentPreviewBo documentPreviewBo = new DocumentPreviewBo();
            documentPreviewBo.setPreviewUrl(res.getPreviewURL());
            documentPreviewBo.setAccessToken(res.getAccessToken());
            documentPreviewBo.setRefreshToken(res.getRefreshToken());
            documentPreviewBo.setFileName(file.getFileName());
            documentPreviewBo.setZoneId(String.valueOf(file.getZoneId()));
            return  ResponseResult.responseSuccessResult(documentPreviewBo);
        } catch (ClientException e) {
            e.printStackTrace();
            return  ResponseResult.responseResultWithErrorCode(ErrorCodes.GET_FILE_URL_ERROR);
        }
    }

    @Override
    public ResponseResult refreshSign(DocumentSignDto documentSignDto) {
        //通过fileId找到路径名称
        File file = fileMapper.selectByPrimaryKey(Long.valueOf(documentSignDto.getFileId()));
        if (ValidateUtil.isEmpty(file)){
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.SELECT_NOT_FOUND);
        }
        IAcsClient client = new DefaultAcsClient(DefaultProfile.getProfile(immUtil.getEndpoint(), immUtil.getAccessKeyId(),immUtil.getAccessKeySecret()));
        RefreshOfficePreviewTokenRequest req = new RefreshOfficePreviewTokenRequest();
        req.setProject(immUtil.getProjectName());
        // 设置待转换对文件OSS路径
        req.setAccessToken(documentSignDto.getAccessToken());
        req.setRefreshToken(documentSignDto.getRefreshToken());

        RefreshOfficePreviewTokenResponse res = null;
        try {
            res = client.getAcsResponse(req);

            DocumentRefreshBo documentRefreshBo = new DocumentRefreshBo();
            documentRefreshBo.setAccessToken(res.getAccessToken());
            documentRefreshBo.setRefreshToken(res.getRefreshToken());
            documentRefreshBo.setFileName(file.getFileName());
            documentRefreshBo.setZoneId(String.valueOf(file.getZoneId()));
            return  ResponseResult.responseSuccessResult(documentRefreshBo);
        } catch (ClientException e) {
            e.printStackTrace();
            return  ResponseResult.responseResultWithErrorCode(ErrorCodes.GET_FILE_URL_ERROR);
        }
    }
}
