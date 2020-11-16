package com.oss.pojo.bo;

import lombok.Data;

/**
 * @ClassName：DocumentPreviewBo
 * @Description: 文档预览
 * @Author：13738700108
 * @Data 2020/11/1 23:13
 * @Version: v1.0
 **/
@Data
public class DocumentPreviewBo {
    private String previewUrl;
    private String accessToken;
    private String refreshToken;
    private String fileName;
    private String zoneId;
}
