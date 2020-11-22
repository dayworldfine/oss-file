package com.oss.tool.shiro;

import com.oss.model.Permission;
import com.oss.model.Role;
import com.oss.model.User;
import com.oss.service.UserService;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.ValidateUtil;
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
import java.util.stream.Collectors;


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

       User user = (User)principalCollection.getPrimaryPrincipal();
       ResponseResult<List<Role>> roleResult = userService.selectRoleByUserId(user.getId());
        List<Role> roleList = roleResult.getData();
        List<String> roles = roleList.stream().map(p -> p.getCode()).collect(Collectors.toList());
        List<String> permissions =new ArrayList<>();
        if (ValidateUtil.isNotEmpty(roleList)){
            String roleJoin = roleList.stream().map(p->String.valueOf(p.getId())).collect(Collectors.joining(","));
           ResponseResult<List<Permission>> permissionResult = userService.selectPermissionByRoleIds(roleJoin);
            permissions=permissionResult.getData().stream().map(p->p.getCode()).collect(Collectors.toList());
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
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
        String account =(String)token.getPrincipal();
        ResponseResult<User> userByAccount = userService.findUserByAccount(account);
        User user = userByAccount.getData();
        if (ValidateUtil.isEmpty(user)){
            return null;
        }
        String pwd= user.getPwd();
        if (pwd == null || "".equals(pwd)){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPwd(),this.getClass().getName());
    }
}
