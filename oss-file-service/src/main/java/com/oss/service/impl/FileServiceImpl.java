package com.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oss.mapper.FileMapper;
import com.oss.model.File;
import com.oss.pojo.bo.FileBo;
import com.oss.pojo.dto.FileListDto;
import com.oss.service.FileService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.OssUtil;
import com.oss.tool.util.SnowUtil;
import com.oss.tool.util.ValidateUtil;
import net.sf.saxon.trans.Err;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
@Transactional
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private OssUtil ossUtil;


    @Override
    public ResponseResult<PageInfo<FileBo>> pageFileByZoneId(FileListDto fileListDto) {
        PageHelper.startPage(fileListDto.getPage(),fileListDto.getSize());
        Page<FileBo> pageInfo = fileMapper.pageFileByZoneId(fileListDto);
        PageInfo<FileBo> pageHelper = new PageInfo<FileBo>(pageInfo);
        return ResponseResult.responseSuccessResult(pageHelper);
    }

    @Override
    public ResponseResult findFileByNameAndZoneId(String fileName, String zoneId) {
        File file =  fileMapper.findFileByNameAndZoneId(fileName,zoneId);
        return ValidateUtil.isEmpty(file)?ResponseResult.responseOK():ResponseResult.responseResultWithErrorCode(ErrorCodes.FILE_NAME_HAVE);
    }

    @Override
    public ResponseResult uploadFile(MultipartFile file, String prefix) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ossUtil.getEndpoint(),ossUtil.getAccessKeyId(), ossUtil.getAccessKeySecret());
        // 上传文件流。
        InputStream inputStream =null;
        try {
            inputStream=   file.getInputStream();
        }catch (IOException e){
            e.printStackTrace();
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.FILE_SUFFIX);
        }
        //上传拼接路径
        String url = prefix+"/"+file.getOriginalFilename();
        ossClient.putObject(ossUtil.getBucketName(), url,inputStream );
        // 关闭OSSClient。
        ossClient.shutdown();
        return ResponseResult.responseOK();
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
        file1.setSuffix(filename.substring(filename.lastIndexOf(".")+1));
        file1.setUrl(prefix+"/"+filename);
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

    /**
     * 通过id删除文件
     * @param fileId
     * @return
     */
    @Override
    public ResponseResult delFileById(String fileId) {
        /**
         * 1.通过文件找到前缀
         * 2.删除OSS
         * 3.删除数据库
         */
        File file= fileMapper.selectByPrimaryKey(Long.valueOf(fileId));
        if (ValidateUtil.isEmpty(file)){
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.FILE_NOT_FOUND);
        }
        OSS ossClient = new OSSClientBuilder().build(ossUtil.getEndpoint(), ossUtil.getAccessKeyId(), ossUtil.getAccessKeySecret());
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(ossUtil.getBucketName(), file.getUrl());
        // 关闭OSSClient。
        ossClient.shutdown();
        Integer integer = fileMapper.deleteByPrimaryKey(Long.valueOf(fileId));
        return ResponseResult.responseOK();
    }

    @Override
    public File selectFileByFileId(String fileId) {
        File file = fileMapper.selectByPrimaryKey(Long.valueOf(fileId));
        return file;
    }


}
