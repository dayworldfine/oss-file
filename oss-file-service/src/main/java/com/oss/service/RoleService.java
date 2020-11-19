package com.oss.service;

import com.oss.config.BaseService;
import com.oss.pojo.dto.RoleAllotDto;
import com.oss.pojo.dto.ZoneAllotDto;
import com.oss.tool.ResponseResult;

/**
 * @InterfaceName：RoleService
 * @Description: 角色服务
 * @Author：13738700108
 * @Data 2020/11/3 0:05
 * @Version: v1.0
 **/
public interface RoleService extends BaseService {

    /**
     * 查询角色列表
     * @return
     */
    ResponseResult getRoleList();

    /**
     * 分配权限
     * @param roleAllotDto
     * @return
     */
    ResponseResult allotRole(RoleAllotDto roleAllotDto);

    /**
     * 分配角色
     * @param zoneAllotDto
     * @return
     */
    ResponseResult allotZone(ZoneAllotDto zoneAllotDto);


    /**
     * 初始化项目
     * @return
     */
    ResponseResult start();
}
