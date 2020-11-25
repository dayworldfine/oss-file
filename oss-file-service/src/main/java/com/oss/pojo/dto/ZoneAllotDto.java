package com.oss.pojo.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ZoneAllotDto {
    @NotNull(message = "userId不得为空")
    private List<String> userIdList = Lists.newArrayList();
    @NotNull(message = "zoneIdList不得为空")
    private List<String> zoneIdList = Lists.newArrayList();
}
