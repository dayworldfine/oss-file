package com.oss.pojo.vo;

import lombok.Data;

/**
 * @ClassName：ZoneVo
 * @Description:
 * @Author：13738700108
 * @Data 2020/11/29 2:20
 * @Version: v1.0
 **/
@Data
public class ZoneVo {

    /**
     * id
     * 2020-10-20T17:12:13.961
     */
    private String id;

    /**
     * 创建时间
     * 2020-10-20T17:12:13.961
     */
    private String createTime;

    /**
     * 修改时间
     * 2020-10-20T17:12:13.961
     */
    private String updateTime;

    /**
     * 版本号
     * 2020-10-20T17:12:13.961
     */
    private String version;

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
}
