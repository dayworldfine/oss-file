package com.oss.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName：RoleStatusDto
 * @Description: 角色状态
 * @Author：13738700108
 * @Data 2020/11/3 0:25
 * @Version: v1.0
 **/
@Data
public class RoleStatusDto {
    private String roleId;
    private Integer status;
}
