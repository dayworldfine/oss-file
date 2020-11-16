package com.oss.pojo.vo;

import lombok.Data;

/**
 * @ClassName：ImmDocumentVo
 * @Description:   文档返回
 * @Author：13738700108
 * @Data 2020/11/1 23:20
 * @Version: v1.0
 **/
@Data
public class ImmDocumentVo {
    private String previewUrl;
    private String accessToken;
    private String refreshToken;
}
