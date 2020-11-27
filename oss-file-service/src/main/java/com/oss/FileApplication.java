package com.oss;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.imm.model.v20170906.*;
import com.aliyuncs.profile.DefaultProfile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@MapperScan(basePackages = {"com.oss.mapper"})
public class FileApplication {

    public void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }


    public static void main(String[] args) {

        SpringApplication.run(FileApplication.class, args);

//        try {
//            FileApplication generatorSqlmap = new FileApplication();
//            generatorSqlmap.generator();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //同步转换
//        docDemo();
        //异步转换
//        docConvertDemo();
        //获取文档预览地址
//        showText();
        //刷新凭证
//        refreshSign();
    }

    // 初始化 IMM 客户端
    static IAcsClient client = new DefaultAcsClient(DefaultProfile.getProfile("cn-hangzhou", "LTAIugLGMMRE80v9","bLmjLAOpVXUKoPLCOQXbbY27Vh3VPX"));
    // 项目名称，请确保该项目已经创建
    static String projectName = "document";

    private static void refreshSign() {
        RefreshOfficePreviewTokenRequest req = new RefreshOfficePreviewTokenRequest();
        req.setProject(projectName);
        // 设置待转换对文件OSS路径
        req.setAccessToken("ac37772f8e444b258f21d89e709e16fav2");
        req.setRefreshToken("781b7aaaf4d14dbeb80bec3961281823v2");


        RefreshOfficePreviewTokenResponse res = null;
        try {
            res = client.getAcsResponse(req);
            System.out.println("res: "+res.getAccessToken());
            System.out.println("res: "+res.getRefreshToken());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    private static void showText() {
        GetOfficePreviewURLRequest req = new GetOfficePreviewURLRequest();
        req.setProject(projectName);
        // 设置待转换对文件OSS路径
        req.setSrcUri("oss://tom-file/ywm/新建文本文档 (4).txt");


        GetOfficePreviewURLResponse res = null;
        try {
            res = client.getAcsResponse(req);
            System.out.println("res: "+res.getPreviewURL());
            System.out.println("res: "+res.getAccessToken());
            String previewURL = res.getPreviewURL();
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

    private static void docDemo() {
    }

    private static void docConvertDemo() {
        CreateOfficeConversionTaskRequest req = new CreateOfficeConversionTaskRequest();
        req.setProject(projectName);
        // 设置待转换对文件OSS路径
        req.setSrcUri("oss://tom-file/ywm/test.pptx");
        // 设置文件输出格式为 vector
        req.setTgtType("vector");
        // 设置转换后的输出路径
        req.setTgtUri("oss://tom-file/ywm/newTest");

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
        getOfficeConversionTaskRequest.setProject(projectName);
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
