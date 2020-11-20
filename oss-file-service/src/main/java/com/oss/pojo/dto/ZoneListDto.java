package com.oss.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ZoneListDto {

    private String Name;
    @NotNull(message = "页数不得为空")
    private Integer page;
    @NotNull(message = "条数不得为空")
    private Integer size;
}
