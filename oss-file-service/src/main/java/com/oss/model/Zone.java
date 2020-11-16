package com.oss.model;

import java.io.Serializable;

public class Zone implements Serializable {
    /**
     * id
	 * 2020-10-20T17:12:13.961
     */
    private Long id;

    /**
     * 创建时间
	 * 2020-10-20T17:12:13.961
     */
    private Long createTime;

    /**
     * 修改时间
	 * 2020-10-20T17:12:13.961
     */
    private Long updateTime;

    /**
     * 版本号
	 * 2020-10-20T17:12:13.961
     */
    private Long version;

    /**
     * 分区名称
	 * 2020-10-20T17:12:13.962
     */
    private String zoneName;

    /**
     * 分区前缀
	 * 2020-10-20T17:12:13.962
     */
    private String zonePrefix;

    /**
     * 分区密钥
	 * 2020-10-20T17:12:13.962
     */
    private String zonePwd;

    /**
     * 是否公开
	 * 2020-10-20T17:12:13.962
     */
    private Integer isOpen;

    /**
     * 2020-10-20T17:12:13.962
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
     * 分区名称
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * 分区名称
     */
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName == null ? null : zoneName.trim();
    }

    /**
     * 分区前缀
     */
    public String getZonePrefix() {
        return zonePrefix;
    }

    /**
     * 分区前缀
     */
    public void setZonePrefix(String zonePrefix) {
        this.zonePrefix = zonePrefix == null ? null : zonePrefix.trim();
    }

    /**
     * 分区密钥
     */
    public String getZonePwd() {
        return zonePwd;
    }

    /**
     * 分区密钥
     */
    public void setZonePwd(String zonePwd) {
        this.zonePwd = zonePwd == null ? null : zonePwd.trim();
    }

    /**
     * 是否开启解密功能(常规枚举)
     */
    public Integer getIsOpen() {
        return isOpen;
    }

    /**
     * 是否开启解密功能(常规枚举)
     */
    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
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
        sb.append(", zoneName=").append(zoneName);
        sb.append(", zonePrefix=").append(zonePrefix);
        sb.append(", zonePwd=").append(zonePwd);
        sb.append(", isOpen=").append(isOpen);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
