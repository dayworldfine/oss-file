package com.oss.pojo.bo;

import lombok.Data;

/**
 * @ClassName：DocumentRefreshBo
 * @Description:
 * @Author：13738700108
 * @Data 2020/11/1 23:38
 * @Version: v1.0
 **/
@Data
public class DocumentRefreshBo {
    private String accessToken;
    private String refreshToken;
    private String fileName;
    private String zoneId;
}
