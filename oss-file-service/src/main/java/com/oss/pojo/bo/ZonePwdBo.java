package com.oss.pojo.bo;

import lombok.Data;

/**
 * @ClassName：ZonePwdBo
 * @Description: 分区密匙
 * @Author：13738700108
 * @Data 2020/11/8 17:53
 * @Version: v1.0
 **/
@Data
public class ZonePwdBo {
    private String id ;
    private String zoneName ;
    private String zonePrefix ;
    private String zonePwd ;
}
