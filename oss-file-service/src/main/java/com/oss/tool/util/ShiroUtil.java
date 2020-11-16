package com.oss.tool.util;

import com.oss.model.User;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    /**
     * 获取用户id
     * @return
     */
    public static long getUserId(){
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Long userId = user.getId();
        return userId;
    }
}
