package com.oss.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ZoneDto {
    @NotNull(message = "分区名称不得为空")
    private String zoneName;
    @NotNull(message = "分区前缀不得为空")
    private String zonePrefix;
    @NotNull(message = "分区密码不得为空")
    private String zonePwd;
    @NotNull(message = "是否公开不得为空")
    private Integer isOpen;
}
