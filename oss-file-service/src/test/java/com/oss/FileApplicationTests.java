package com.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.imm.model.v20170906.CreateOfficeConversionTaskRequest;
import com.aliyuncs.imm.model.v20170906.CreateOfficeConversionTaskResponse;
import com.aliyuncs.imm.model.v20170906.GetOfficeConversionTaskRequest;
import com.aliyuncs.imm.model.v20170906.GetOfficeConversionTaskResponse;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.oss.tool.util.ImmUtil;
import com.oss.tool.util.OssUtil;

import java.io.*;

@SpringBootTest
class FileApplicationTests {

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private ImmUtil immUtil;



    /**
     * 上传
     */
    @Test
    void contextLoads() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ossUtil.getEndpoint();
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ossUtil.getAccessKeyId();
        String accessKeySecret = ossUtil.getAccessKeySecret();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        File f = new File("C:\\Users\\13738700108\\Desktop\\新建文本文档 (4).txt");
        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String url = "ywm/新建文本文档 (4).txt";
        ossClient.putObject(ossUtil.getBucketName(), url, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();


    }


    /**
     * 同步转换
     */
    @Test
    void ConvertOfficeFormat(){

    }

    /**
     * 异步转换
     */
    @Test
    void CreateOfficeConversionTask(){
        IAcsClient client = new DefaultAcsClient(DefaultProfile.getProfile(immUtil.getEndpoint(), immUtil.getAccessKeyId(),immUtil.getAccessKeySecret()));
        CreateOfficeConversionTaskRequest req = new CreateOfficeConversionTaskRequest();
        req.setProject(immUtil.getProjectName());
        // 设置待转换对文件OSS路径
        req.setSrcUri("oss://ywm/test.pptx");
        // 设置文件输出格式为 vector
        req.setTgtType("vector");
        // 设置转换后的输出路径
        req.setTgtUri("oss://ywm/newTest.pptx");
        CreateOfficeConversionTaskResponse res = null;
        try {
            res = client.getAcsResponse(req);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        String taskId = res.getTaskId();
        // 获取文档转换任务结果，最多轮询 30 次
        // 每次轮询的间隔为 1 秒
        GetOfficeConversionTaskRequest getOfficeConversionTaskRequest = new GetOfficeConversionTaskRequest();
        getOfficeConversionTaskRequest.setProject(immUtil.getProjectName());
        getOfficeConversionTaskRequest.setTaskId(taskId);
        int maxCount = 30;
        int count = 0;
        try {
            while (true) {
                Thread.sleep(1000); // 1 秒
                GetOfficeConversionTaskResponse getOfficeConversionTaskResponse = null;
                try {
                    getOfficeConversionTaskResponse = client.getAcsResponse(getOfficeConversionTaskRequest);
                } catch (ClientException e) {
                    e.printStackTrace();
                }
                if (!getOfficeConversionTaskResponse.getStatus().equals("Running")) {
                    // 输出文档转换任务执行结果
                    System.out.println(getOfficeConversionTaskResponse.getTaskId());
                    System.out.println(getOfficeConversionTaskResponse.getFailDetail().getCode());
                    System.out.println("Done");
                    break;
                }
                count = count + 1;
                if(count >= maxCount) {
                    System.out.println("OfficeConversion Timeout for 30 seconds");
                    break;
                }
                System.out.println("Task is still running.");
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
