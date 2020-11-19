package com.oss.model;

import java.io.Serializable;

public class RoleInfoPermission implements Serializable {

    public RoleInfoPermission() {
    }

    public RoleInfoPermission(Long id, Long createTime, Long updateTime, Long version, Long roleId, Long permissionId) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.version = version;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    /**
     * id
	 * 2020-10-31T00:33:28.919
     */
    private Long id;

    /**
     * 创建时间
	 * 2020-10-31T00:33:28.920
     */
    private Long createTime;

    /**
     * 修改时间
	 * 2020-10-31T00:33:28.920
     */
    private Long updateTime;

    /**
     * 版本号
	 * 2020-10-31T00:33:28.920
     */
    private Long version;

    /**
     * 角色id
	 * 2020-10-31T00:33:28.920
     */
    private Long roleId;

    /**
     * 权限id
	 * 2020-10-31T00:33:28.920
     */
    private Long permissionId;

    /**
     * 2020-10-31T00:33:28.920
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
     * 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 权限id
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 权限id
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", version=").append(version);
        sb.append(", roleId=").append(roleId);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
