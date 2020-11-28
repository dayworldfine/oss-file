package com.oss.pojo.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ZoneAllotDto {
    @NotEmpty(message = "请至少选择一个用户")
    private List<String> userIdList = Lists.newArrayList();
    @NotNull(message = "缺少分区列表参数")
    private List<String> zoneIdList = Lists.newArrayList();
}
