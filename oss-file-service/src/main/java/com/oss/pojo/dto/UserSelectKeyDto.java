package com.oss.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName：UserDto
 * @Description: 用户类
 * @Author：13738700108
 * @Data 2020/11/2 23:02
 * @Version: v1.0
 **/
@Data
public class UserSelectKeyDto {
    private String paramKey;
    @NotNull
    private Integer page;
    @NotNull
    private Integer size;
}
