package com.oss.pojo.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ZoneAllotDto {
    @NotNull(message = "userId不得为空")
    private String userId;
    @NotNull(message = "statusDtoList不得为空")
    private List<ZoneStatusDto> statusDtoList = Lists.newArrayList();
}
