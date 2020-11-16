package com.oss.service.impl;

import com.google.common.collect.Lists;
import com.oss.mapper.RoleMapper;
import com.oss.mapper.UserInfoRoleMapper;
import com.oss.mapper.UserInfoZoneMapper;
import com.oss.model.Role;
import com.oss.model.UserInfoRole;
import com.oss.model.UserInfoZone;
import com.oss.pojo.dto.RoleAllotDto;
import com.oss.pojo.dto.RoleStatusDto;
import com.oss.pojo.dto.ZoneAllotDto;
import com.oss.pojo.dto.ZoneStatusDto;
import com.oss.service.RoleService;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.SnowUtil;
import com.oss.tool.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName：RoleServiceImpl
 * @Description: 角色服务
 * @Author：13738700108
 * @Data 2020/11/3 0:05
 * @Version: v1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserInfoRoleMapper userInfoRoleMapper;

    @Autowired
    private UserInfoZoneMapper userInfoZoneMapper;

    @Override
    public ResponseResult getRoleList() {
        List<Role> roleList = roleMapper.getRoleList();
        return ResponseResult.responseSuccessResult(roleList);
    }

    @Override
    public ResponseResult allotRole(RoleAllotDto roleAllotDto) {
        String userId = roleAllotDto.getUserId();
        //先删除所有权限
        Integer delNum  =   userInfoRoleMapper.deleteByUserId(userId);
        //再分配权限
        List<RoleStatusDto> collect = roleAllotDto.getStatusDtoList().stream().filter(p -> p.getStatus() == 1).collect(Collectors.toList());
        List<UserInfoRole> userInfoRoleList = Lists.newArrayList();
        collect.forEach(a->{
            UserInfoRole userInfoRole = new UserInfoRole();
            userInfoRole.setId(SnowUtil.generateId());
            userInfoRole.setCreateTime(System.currentTimeMillis());
            userInfoRole.setUpdateTime(System.currentTimeMillis());
            userInfoRole.setVersion(1l);
            userInfoRole.setUserId(Long.valueOf(userId));
            userInfoRole.setRoleId(Long.valueOf(a.getRoleId()));
            userInfoRoleList.add(userInfoRole);
        });
        if (ValidateUtil.isNotEmpty(userInfoRoleList)){
            Integer insertNum = userInfoRoleMapper.insertUserInfoRoleList(userInfoRoleList);
        }

        return ResponseResult.responseOK();
    }

    @Override
    public ResponseResult allotZone(ZoneAllotDto zoneAllotDto) {
        String userId = zoneAllotDto.getUserId();
        //先删除所有角色
        Integer delNum  =   userInfoZoneMapper.deleteByUserId(userId);
        //再分配角色
        List<ZoneStatusDto> collect = zoneAllotDto.getStatusDtoList().stream().filter(p -> p.getStatus() == 1).collect(Collectors.toList());
        List<UserInfoZone> userInfoZoneList = Lists.newArrayList();
        collect.forEach(a->{
            UserInfoZone userInfoZone = new UserInfoZone();
            userInfoZone.setId(SnowUtil.generateId());
            userInfoZone.setCreateTime(System.currentTimeMillis());
            userInfoZone.setUpdateTime(System.currentTimeMillis());
            userInfoZone.setVersion(1l);
            userInfoZone.setUserId(Long.valueOf(userId));
            userInfoZone.setZoneId(Long.valueOf(a.getZoneId()));
            userInfoZoneList.add(userInfoZone);
        });
        if (ValidateUtil.isNotEmpty(userInfoZoneList)){
            Integer insertNum = userInfoZoneMapper.insertUserInfoZoneList(userInfoZoneList);
        }

        return ResponseResult.responseOK();
    }


}
