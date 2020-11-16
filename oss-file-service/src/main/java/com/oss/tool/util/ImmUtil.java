package com.oss.tool.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName：ImmUtil
 * @Description:
 * @Author：13738700108
 * @Data 2020/10/16 23:23
 * @Version: v1.0
 **/
@Component
public class ImmUtil {
    @Value("${aliyun.imm.endpoint}")
    private String endpoint;

    @Value("${aliyun.imm.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.imm.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.imm.projectName}")
    private String projectName;

    public String getEndpoint() {
        return endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getProjectName() {
        return projectName;
    }
}
