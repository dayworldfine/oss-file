package com.oss.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDto {
    @NotNull(message = "手机号不得为空")
    private String account;
    @NotNull(message = "昵称不得为空")
    private String nickName;
    @NotNull(message = "验证码不得为空")
    private String code;
    @NotNull(message = "密码不得为空")
    private String pwd;
}
