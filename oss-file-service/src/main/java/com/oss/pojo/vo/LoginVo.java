package com.oss.pojo.vo;

import com.oss.model.User;
import com.oss.tool.Identity;
import lombok.Data;

/**
 * @ClassName：LoginVo
 * @Description:
 * @Author：13738700108
 * @Data 2020/11/22 21:32
 * @Version: v1.0
 **/
@Data
public class LoginVo {
    private String token;
    private Identity identity;
}
