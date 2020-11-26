package com.oss.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FileListDto {

    @NotNull(message = "分区id不得为空")
    private String zoneId;
    private String name;
    @NotNull(message = "页数不得为空")
    private Integer page;
    @NotNull(message = "条数不得为空")
    private Integer size;
}
