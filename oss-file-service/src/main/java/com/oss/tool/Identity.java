package com.oss.tool;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class Identity {
    private String userId;
    private String userImg;
    private String userNickName;
    private List<String> userRoleName = Lists.newArrayList();

    public Identity() {
    }

    public Identity(String userId, String userImg, String userNickName, List<String> userRoleName) {
        this.userId = userId;
        this.userImg = userImg;
        this.userNickName = userNickName;
        this.userRoleName = userRoleName;
    }
}
