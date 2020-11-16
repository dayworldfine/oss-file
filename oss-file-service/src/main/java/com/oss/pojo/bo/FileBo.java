package com.oss.pojo.bo;

import lombok.Data;

/**
 * @ClassName：FileBo
 * @Description:
 * @Author：13738700108
 * @Data 2020/10/31 20:33
 * @Version: v1.0
 **/
@Data
public class FileBo {
    private String id;
    private String createTime;
    private String updateTime;
    private String fileName;
    private String suffix;
    private String url;
    private String uploadUserId;
    private String uploadUserName;
    private String uploadUserImg;
    private String downloadStatistics;
    private String previewStatistics;
}
