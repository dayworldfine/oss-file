package com.oss.tool.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName：SmsUtil
 * @Description: 短信工具类
 * @Author：13738700108
 * @Data 2020/10/6 19:11
 * @Version: v1.0
 **/
@Component
public class SmsUtil {

    @Value("${aliyun.sms.regionId}")
    private String regionId;

    @Value("${aliyun.sms.SignName}")
    private String SignName;

    @Value("${aliyun.sms.TemplateCode}")
    private String TemplateCode;

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.sms.secret}")
    private String secret;

    public String getRegionId() {
        return regionId;
    }

    public String getSignName() {
        return SignName;
    }

    public String getTemplateCode() {
        return TemplateCode;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getSecret() {
        return secret;
    }

    /**
     * 产生6位数的随机验证码
     * @return
     */
    public static String generalRandomVeriCode(){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<6;i++){
            int random=(int)(Math.random()*9+1);
            System.out.println(random);
            sb.append(random);
        }
        return sb.toString();
    }
}
