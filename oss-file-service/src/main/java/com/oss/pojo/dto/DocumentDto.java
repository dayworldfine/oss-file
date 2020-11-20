package com.oss.pojo.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName：DocumentDto
 * @Description: 测试
 * @Author：13738700108
 * @Data 2020/10/29 22:49
 * @Version: v1.0
 **/
@Data
public class DocumentDto {

    @Max(value = 10, message = "最大值不超过10")
    private int sadasd;

    @NotBlank(message = "离开你第一百个星期")
    private String documentId;
}
