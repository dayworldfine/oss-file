package com.oss.tool.shiro;

import com.oss.model.User;
import org.apache.shiro.SecurityUtils;

public class ShiroHandler {

    /**
     * 获取用户id
     * @return
     */
    public static Long getUserId(){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            Long userId = user.getId();
            return userId;
        }catch (Exception e){
            return null;
        }
    }
}
