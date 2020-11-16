package com.oss.model;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * id
	 * 2020-10-20T17:12:13.952
     */
    private Long id;

    /**
     * 创建时间
	 * 2020-10-20T17:12:13.952
     */
    private Long createTime;

    /**
     * 修改时间
	 * 2020-10-20T17:12:13.952
     */
    private Long updateTime;

    /**
     * 版本号
	 * 2020-10-20T17:12:13.952
     */
    private Long version;

    /**
     * 账号
	 * 2020-10-20T17:12:13.952
     */
    private String userName;

    /**
     * 昵称
     * 2020-10-20T17:12:13.952
     */
    private String nickName;

    /**
     * 昵称
     * 2020-10-20T17:12:13.952
     */
    private String headPortrait;

    /**
     * 手机号
	 * 2020-10-20T17:12:13.953
     */
    private Long account;

    /**
     * 密码
	 * 2020-10-20T17:12:13.953
     */
    private String pwd;

    /**
     * 2020-10-20T17:12:13.953
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


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 手机号
     */
    public Long getAccount() {
        return account;
    }

    /**
     * 手机号
     */
    public void setAccount(Long account) {
        this.account = account;
    }

    /**
     * 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", version=" + version +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", account=" + account +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
