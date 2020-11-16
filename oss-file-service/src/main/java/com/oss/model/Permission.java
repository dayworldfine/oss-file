package com.oss.model;

import java.io.Serializable;

public class Permission implements Serializable {
    /**
     * id
	 * 2020-10-20T17:12:13.944
     */
    private Long id;

    /**
     * 创建时间
	 * 2020-10-20T17:12:13.944
     */
    private Long createTime;

    /**
     * 修改时间
	 * 2020-10-20T17:12:13.944
     */
    private Long updateTime;

    /**
     * 版本号
	 * 2020-10-20T17:12:13.944
     */
    private Long version;

    /**
     * 权限名称
	 * 2020-10-20T17:12:13.944
     */
    private String name;

    /**
     * 权限url
	 * 2020-10-20T17:12:13.944
     */
    private String url;


    /**
     * 2020-10-20T17:12:13.944
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
     * 权限名称
     */
    public String getName() {
        return name;
    }

    /**
     * 权限名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 权限url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 权限url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
