package com.oss.model;

import java.io.Serializable;

public class Role implements Serializable {

    public Role() {
    }

    public Role(Long id, Long createTime, Long updateTime, Long version, String roleName, String code, String pwd, Integer isOpen) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.version = version;
        this.roleName = roleName;
        this.code = code;
        this.pwd = pwd;
        this.isOpen = isOpen;
    }

    /**
     * id
	 * 2020-10-20T17:12:13.949
     */
    private Long id;

    /**
     * 创建时间
	 * 2020-10-20T17:12:13.949
     */
    private Long createTime;

    /**
     * 修改时间
	 * 2020-10-20T17:12:13.949
     */
    private Long updateTime;

    /**
     * 版本号
	 * 2020-10-20T17:12:13.949
     */
    private Long version;

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

    /**
     * 2020-10-20T17:12:13.949
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 版本号
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", version=" + version +
                ", roleName='" + roleName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", isOpen=" + isOpen +
                '}';
    }
}
