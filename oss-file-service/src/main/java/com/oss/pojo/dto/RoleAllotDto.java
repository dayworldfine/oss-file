package com.oss.pojo.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "请至少选择一个用户")
    private List<String> userIdList = Lists.newArrayList();
    @NotNull(message = "缺少角色列表参数")
    private List<String> roleIdList = Lists.newArrayList();
}
