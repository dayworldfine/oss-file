package com.oss.pojo.vo;

import lombok.Data;

/**
 * @ClassName：RoleVo
 * @Description:
 * @Author：13738700108
 * @Data 2020/11/29 2:16
 * @Version: v1.0
 **/
@Data
public class RoleVo {

    /**
     * id
     * 2020-10-20T17:12:13.949
     */
    private String id;

    /**
     * 创建时间
     * 2020-10-20T17:12:13.949
     */
    private String createTime;

    /**
     * 修改时间
     * 2020-10-20T17:12:13.949
     */
    private String updateTime;

    /**
     * 版本号
     * 2020-10-20T17:12:13.949
     */
    private String version;

    /**
     * 角色名称
     * 2020-10-20T17:12:13.949
     */
    private String roleName;

    /**
     * 权限密码
     * 2020-10-20T17:12:13.944
     */
    private String code;

    /**
     * 权限密码
     * 2020-10-20T17:12:13.944
     */
    private String pwd;

    /**
     * 权限密码
     * 2020-10-20T17:12:13.944
     */
    private Integer isOpen;
}
