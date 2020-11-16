package com.oss.model;

import java.io.Serializable;

public class File implements Serializable {
    /**
     * id
	 * 2020-10-20T17:12:13.912
     */
    private Long id;

    /**
     * 创建时间
	 * 2020-10-20T17:12:13.914
     */
    private Long createTime;

    /**
     * 修改时间
	 * 2020-10-20T17:12:13.914
     */
    private Long updateTime;

    /**
     * 版本号
	 * 2020-10-20T17:12:13.914
     */
    private Long version;


    /**
     * 文件名
	 * 2020-10-20T17:12:13.914
     */
    private String fileName;

    /**
     * 后缀
	 * 2020-10-20T17:12:13.915
     */
    private String suffix;

    /**
     * 访问路径
	 * 2020-10-20T17:12:13.915
     */
    private String url;

    /**
     * 上传用户id
	 * 2020-10-20T17:12:13.915
     */
    private Long uploadUserId;


    /**
     * 下载次数统计
	 * 2020-10-20T17:12:13.915
     */
    private Long downloadStatistics;

    /**
     * 预览次数统计
	 * 2020-10-20T17:12:13.915
     */
    private Long previewStatistics;

    /**
     * 分区id
	 * 2020-10-20T17:12:13.915
     */
    private Long zoneId;

    /**
     * 2020-10-20T17:12:13.915
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
     * 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 后缀
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 后缀
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    /**
     * 访问路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 访问路径
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 上传用户id
     */
    public Long getUploadUserId() {
        return uploadUserId;
    }

    /**
     * 上传用户id
     */
    public void setUploadUserId(Long uploadUserId) {
        this.uploadUserId = uploadUserId;
    }



    /**
     * 下载次数统计
     */
    public Long getDownloadStatistics() {
        return downloadStatistics;
    }

    /**
     * 下载次数统计
     */
    public void setDownloadStatistics(Long downloadStatistics) {
        this.downloadStatistics = downloadStatistics;
    }

    /**
     * 预览次数统计
     */
    public Long getPreviewStatistics() {
        return previewStatistics;
    }

    /**
     * 预览次数统计
     */
    public void setPreviewStatistics(Long previewStatistics) {
        this.previewStatistics = previewStatistics;
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
        sb.append(", fileName=").append(fileName);
        sb.append(", suffix=").append(suffix);
        sb.append(", url=").append(url);
        sb.append(", uploadUserId=").append(uploadUserId);
        sb.append(", downloadStatistics=").append(downloadStatistics);
        sb.append(", previewStatistics=").append(previewStatistics);
        sb.append(", zoneId=").append(zoneId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
