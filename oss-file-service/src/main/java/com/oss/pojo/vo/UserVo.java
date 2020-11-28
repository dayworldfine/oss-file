package com.oss.pojo.vo;

import lombok.Data;

/**
 * @ClassName：UserVo
 * @Description:
 * @Author：13738700108
 * @Data 2020/11/29 2:22
 * @Version: v1.0
 **/
@Data
public class UserVo {

    /**
     * id
     * 2020-10-20T17:12:13.952
     */
    private String id;

    /**
     * 创建时间
     * 2020-10-20T17:12:13.952
     */
    private String createTime;

    /**
     * 修改时间
     * 2020-10-20T17:12:13.952
     */
    private String updateTime;

    /**
     * 版本号
     * 2020-10-20T17:12:13.952
     */
    private String version;


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
}
