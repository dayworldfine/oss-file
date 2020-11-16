package com.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.oss.service.UploadService;
import com.oss.tool.util.OssUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private OssUtil ossUtil;

    @Override
    public ResponseResult uploadFile(MultipartFile file, String prefix) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);


        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ossUtil.getEndpoint();
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ossUtil.getAccessKeyId();
        String accessKeySecret = ossUtil.getAccessKeySecret();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

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
    public ResponseResult delPrefixFile(String prefix) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ossUtil.getEndpoint(), ossUtil.getAccessKeyId(), ossUtil.getAccessKeySecret());

        // 列举所有包含指定前缀的文件并删除。
        String nextMarker = null;
        ObjectListing objectListing = null;
        do {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(ossUtil.getBucketName())
                    .withPrefix(prefix)
                    .withMarker(nextMarker);

            objectListing = ossClient.listObjects(listObjectsRequest);
            if (objectListing.getObjectSummaries().size() > 0) {
                List<String> keys = new ArrayList<String>();
                for (OSSObjectSummary s : objectListing.getObjectSummaries()) {
                    System.out.println("key name: " + s.getKey());
                    keys.add(s.getKey());
                }
                DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(ossUtil.getBucketName()).withKeys(keys);
                ossClient.deleteObjects(deleteObjectsRequest);
            }

            nextMarker = objectListing.getNextMarker();
        } while (objectListing.isTruncated());

        // 关闭OSSClient。
        ossClient.shutdown();
        return ResponseResult.responseOK();
    }
}
