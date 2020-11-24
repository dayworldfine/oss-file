package com.oss.service.impl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oss.mapper.*;
import com.oss.model.*;
import com.oss.pojo.dto.RegisterDto;
import com.oss.pojo.dto.UserSelectKeyDto;
import com.oss.service.UserService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseResult;
import com.oss.tool.param.OssParam;
import com.oss.tool.util.MD5Util;
import com.oss.tool.util.OssUtil;
import com.oss.tool.util.SnowUtil;
import com.oss.tool.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @Autowired
    private OssUtil ossUtil;

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

    /**
     * 查询用户拥有角色(鉴权)
     * @param userId
     * @return
     */
    @Override
    public ResponseResult<List<Role>> selectRoleByUserId(Long userId) {
        List<Role> roleList = userMapper.selectRoleByUserId(userId);
        return ResponseResult.responseSuccessResult(roleList);
    }

    /**
     * 查询用户拥有权限(鉴权)
     * @param roleJoin
     * @return
     */
    @Override
    public ResponseResult<List<Permission>> selectPermissionByRoleIds(String roleJoin) {
        List<Permission> permissionList = userMapper.selectPermissionByRoleIds(roleJoin);
        return ResponseResult.responseSuccessResult(permissionList);
    }


    @Override
    public ResponseResult<List<Role>> getMyRolePwd(long userId) {
        List<Role> roleList = userMapper.getMyRolePwd(userId);
        return ResponseResult.responseSuccessResult(roleList);
    }

    /**
     * 修改用户昵称
     * @param userName
     * @return
     */
    @Override
    public ResponseResult updateUserName(String userName,long userId) {
        Integer num = userMapper.updateUserName(userName,userId);
        return ValidateUtil.isNotCountEmpty(num)?ResponseResult.responseOK():ResponseResult.responseResultWithErrorCode(ErrorCodes.UPDATE_ERROR);
    }

    /**
     * 修改用户头像
     * @param file
     * @param userId
     * @return
     */
    @Override
    public ResponseResult updateUserImg(MultipartFile file, Long userId) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);


        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ossUtil.getEndpoint();
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ossUtil.getAccessKeyId();
        String accessKeySecret = ossUtil.getAccessKeySecret();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream =null;
        try {
            inputStream=   file.getInputStream();
        }catch (IOException e){
            e.printStackTrace();
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.FILE_SUFFIX);
        }
        //上传拼接路径
        String url = OssParam.USER_IMG_PREFIX +"/"+SnowUtil.generateId()+file.getOriginalFilename();
        ossClient.putObject(ossUtil.getBucketName(), url,inputStream );

        // 关闭OSSClient。
        ossClient.shutdown();

        Integer num = userMapper.updateUserImg(userId,"/"+url);

        return  ValidateUtil.isNotCountEmpty(num)?ResponseResult.responseSuccessResult("/"+url):ResponseResult.responseResultWithErrorCode(ErrorCodes.UPDATE_ERROR);
    }
}
