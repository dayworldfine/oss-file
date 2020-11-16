package com.oss.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ZoneListDto {

    private String Name;
    @NotNull
    private Integer page;
    @NotNull
    private Integer size;
}
