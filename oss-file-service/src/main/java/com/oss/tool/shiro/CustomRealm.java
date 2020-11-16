package com.oss.tool.shiro;

import com.oss.model.User;
import com.oss.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName：CustomRealm
 * @Description: 自定义Realm
 * @Author：13738700108
 * @Data 2020/2/15 20:34
 * @Version: v1.0
 **/
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     *进行权限校验的时候会调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-----------------进行权限校验的时候会调用-----------------");
        List<String> aaa =new ArrayList<>();
        List<String> bbb =new ArrayList<>();
        aaa.add("111");
        aaa.add("222");
        bbb.add("333");
        bbb.add("444");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(aaa);
        simpleAuthorizationInfo.addStringPermissions(bbb);
        return null;
    }

    /**
     * 用户登录的时候会调用
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("-----------------用户登录的时候会调用-----------------");
        //从token获得用户信息，token代表用户输入
        String userName =(String)token.getPrincipal();
        User user = userService.findUserByUserName(userName);
        if (null==user){
            return null;
        }
        String pwd= user.getPwd();
        if (pwd == null || "".equals(pwd)){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPwd(),this.getClass().getName());
    }
}
