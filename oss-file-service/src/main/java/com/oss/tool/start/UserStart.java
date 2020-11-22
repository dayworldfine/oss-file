package com.oss.tool.start;

import com.oss.model.Permission;
import com.oss.service.RoleService;
import com.oss.service.UserService;
import com.oss.service.ZoneService;
import com.oss.tool.ResponseResult;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName：UserStart
 * @Description: 用户相关启动类
 * @Author：13738700108
 * @Data 2020/11/19 22:02
 * @Version: v1.0
 **/
@Component
public class UserStart {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserStart.class);

    private String userId = "1";
    private String nickName = "wbh";
    private String userImg = "/HeadImg/wbh.jpg";
    private String account = "13738700108";
    private String pwd = "wbh123";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ZoneService zoneService;

    @PostConstruct
    public void cleanSeatUser(){
        LOGGER.info("------------------开始执行用户初始化数据----------------------");
        ResponseResult roleResult = roleService.start();
        ResponseResult userResult = userService.start(userId,nickName,userImg,account,pwd);
        ResponseResult zoneResult = zoneService.start();

        LOGGER.info("------------------插入初始化数据结束----------------------");
    }
}
