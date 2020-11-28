package com.oss.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName：retrievePwdDto
 * @Description:
 * @Author：13738700108
 * @Data 2020/11/29 2:38
 * @Version: v1.0
 **/
@Data
public class RetrievePwdDto {
    @NotNull(message = "手机号不得为空")
    private String account;
    @NotNull(message = "验证码不得为空")
    private String code;
    @NotNull(message = "密码不得为空")
    private String pwd;
}
