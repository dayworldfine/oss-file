package com.oss.pojo.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName：RoleAllotDto
 * @Description: 分配角色
 * @Author：13738700108
 * @Data 2020/11/3 0:24
 * @Version: v1.0
 **/
@Data
public class RoleAllotDto {
    @NotNull(message = "userId不得为空")
    private List<String> userIdList = Lists.newArrayList();
    @NotNull(message = "roleIdList不得为空")
    private List<String> roleIdList = Lists.newArrayList();
}
