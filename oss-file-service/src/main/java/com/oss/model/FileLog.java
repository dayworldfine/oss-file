package com.oss.model;

import java.io.Serializable;

public class FileLog implements Serializable {
    /**
     * id
	 * 2020-10-20T17:12:13.938
     */
    private Long id;

    /**
     * 创建时间
	 * 2020-10-20T17:12:13.938
     */
    private Long createTime;

    /**
     * 修改时间
	 * 2020-10-20T17:12:13.938
     */
    private Long updateTime;

    /**
     * 版本号
	 * 2020-10-20T17:12:13.938
     */
    private Long version;

    /**
     * 文件id
	 * 2020-10-20T17:12:13.938
     */
    private Long fileId;

    /**
     * 文件名称
	 * 2020-10-20T17:12:13.938
     */
    private String fileName;

    /**
     * 操作类型（文件操作枚举）
	 * 2020-10-20T17:12:13.938
     */
    private Integer operationType;



    /**
     * 分区id
	 * 2020-10-20T17:12:13.939
     */
    private Long zoneId;

    /**
     * 操作用户id
	 * 2020-10-20T17:12:13.939
     */
    private Long userId;


    /**
     * 2020-10-20T17:12:13.939
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
     * 文件id
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 文件id
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 操作类型（文件操作枚举）
     */
    public Integer getOperationType() {
        return operationType;
    }

    /**
     * 操作类型（文件操作枚举）
     */
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }


    /**
     * 分区id
     */
    public Long getZoneId() {
        return zoneId;
    }

    /**
     * 分区id
     */
    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * 操作用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 操作用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
        sb.append(", fileId=").append(fileId);
        sb.append(", fileName=").append(fileName);
        sb.append(", operationType=").append(operationType);
        sb.append(", zoneId=").append(zoneId);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
