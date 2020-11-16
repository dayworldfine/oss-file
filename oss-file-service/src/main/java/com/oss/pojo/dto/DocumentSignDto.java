package com.oss.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName：DocumentSignDto
 * @Description: 文档刷新
 * @Author：13738700108
 * @Data 2020/11/1 23:28
 * @Version: v1.0
 **/
@Data
public class DocumentSignDto {

    @NotNull(message = "文件id不得为空")
    private String fileId;
    @NotNull(message = "accessToken不得为空")
    private String accessToken;
    @NotNull(message = "refreshToken不得为空")
    private String refreshToken;
}
