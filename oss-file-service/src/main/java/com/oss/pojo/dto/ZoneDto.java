package com.oss.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ZoneDto {
    @NotNull
    private String zoneName;
    @NotNull
    private String zonePrefix;
    @NotNull
    private String zonePwd;
    @NotNull
    private Integer isOpen;
}
