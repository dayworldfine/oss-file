package com.oss.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oss.mapper.*;
import com.oss.model.*;
import com.oss.pojo.dto.RegisterDto;
import com.oss.pojo.dto.UserSelectKeyDto;
import com.oss.service.UserService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.MD5Util;
import com.oss.tool.util.SnowUtil;
import com.oss.tool.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName：UserServiceImpl
 * @Description: 用户服务
 * @Author：13738700108
 * @Data 2020/10/31 20:21
 * @Version: v1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoZoneMapper userInfoZoneMapper;

    @Autowired
    private UserInfoRoleMapper userInfoRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ZoneMapper zoneMapper;

    @Override
    public User findUserByUserName(String userName) {
        return null;
    }

    @Override
    public ResponseResult pageUserBySelectKey(UserSelectKeyDto userSelectKeyDto) {
        PageHelper.startPage(userSelectKeyDto.getPage(),userSelectKeyDto.getSize());

        Page<User> pageInfo = userMapper.pageSelectKeyUser(userSelectKeyDto.getParamKey());
        return ResponseResult.responseSuccessResult(pageInfo);
    }

    /**
     * 获取分区钥匙
     * @param userId
     * @param pwd
     * @return
     */
    @Override
    public ResponseResult getZoneKey(Long userId, String pwd) {
        /** 查看这个密钥相对于的分区id*/
        Zone zone = zoneMapper.selectByPwd(pwd);
        if (ValidateUtil.isEmpty(zone)){
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.ERROR_ZONE_KEY);
        }
        /** 查看这个用户是否已经拥有这个分区*/
        UserInfoZone userInfoZone = userInfoZoneMapper.selectByZoneId(userId,zone.getId());
        if (ValidateUtil.isNotEmpty(userInfoZone)){
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.ERROR_ZONE_IS_HAVE);
        }
        /**添加记录*/
        UserInfoZone uiz = new UserInfoZone();
        uiz.setId(SnowUtil.generateId());
        uiz.setCreateTime(System.currentTimeMillis());
        uiz.setUpdateTime(System.currentTimeMillis());
        uiz.setVersion(1l);
        uiz.setUserId(userId);
        uiz.setZoneId(zone.getId());
        int insert = userInfoZoneMapper.insert(uiz);

        return ResponseResult.responseOK();
    }

    /**
     * 获取角色钥匙
     * @param userId
     * @param pwd
     * @return
     */
    @Override
    public ResponseResult getRoleKey(long userId, String pwd) {
        /** 查看这个密钥相对于的角色id*/
        Role role = roleMapper.selectByPwd(pwd);
        if (ValidateUtil.isEmpty(role)){
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.ERROR_ZONE_KEY);
        }
        /** 查看这个用户是否已经拥有这个角色*/
        UserInfoRole userInfoRole = userInfoRoleMapper.selectByRoleId(userId,role.getId());
        if (ValidateUtil.isNotEmpty(userInfoRole)){
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.ERROR_ZONE_IS_HAVE);
        }
        /**添加记录*/
        UserInfoRole uir = new UserInfoRole();
        uir.setId(SnowUtil.generateId());
        uir.setCreateTime(System.currentTimeMillis());
        uir.setUpdateTime(System.currentTimeMillis());
        uir.setVersion(1l);
        uir.setUserId(userId);
        uir.setRoleId(role.getId());
        int insert = userInfoRoleMapper.insert(uir);

        return ResponseResult.responseOK();
    }

    /**
     * 初始化项目
     * @param nickName
     * @param userImg
     * @param account
     * @param pwd
     * @return
     */
    @Override
    public ResponseResult start(String userId,String nickName, String userImg, String account, String pwd) {
        /** 1.查看手机号是否已存在 是就不用插入数据*/
        Integer num =  userMapper.selectByAccount(account);
        if (ValidateUtil.isNotCountEmpty(num)){
            return ResponseResult.responseOK();
        }
        User u = new User();
        u.setId(Long.valueOf(userId));
        u.setCreateTime(System.currentTimeMillis());
        u.setUpdateTime(System.currentTimeMillis());
        u.setVersion(1l);
        u.setAccount(Long.valueOf(account));
        u.setHeadPortrait(userImg);
        u.setNickName(nickName);
        u.setPwd(MD5Util.StringMD5(pwd));
        int insert = userMapper.insert(u);
        return ResponseResult.responseOK();
    }

    @Override
    public ResponseResult<User> findUserByAccount(String account) {
        User user = userMapper.selectUserByAccount(account);
        return ResponseResult.responseSuccessResult(user);
    }

    /**
     * 添加用户
     * @param registerDto
     * @return
     */
    @Override
    public ResponseResult addUser(RegisterDto registerDto) {
        User user = new User();
        user.setId(SnowUtil.generateId());
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        user.setVersion(1l);
        user.setNickName(registerDto.getNickName());
        user.setHeadPortrait("HeadImg/defaultUserImg.png");
        user.setAccount(Long.valueOf(registerDto.getAccount()));
        user.setPwd(MD5Util.StringMD5(registerDto.getPwd()));
        int insert = userMapper.insert(user);
        return ValidateUtil.isNotCountEmpty(insert)?ResponseResult.responseOK():ResponseResult.responseResultWithErrorCode(ErrorCodes.ADD_ERROR);
    }

    @Override
    public ResponseResult<List<Role>> selectRoleByUserId(Long id) {
//        userMapper.selectRoleByUserId();
        return null;
    }
}
