package com.oss.tool.shiro;


import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @ClassName: CustomSessionManager
 * @project: ayundao
 * @author: 13620
 * @Date: 2019/7/28
 * @Description: 实现 - 活动
 * @Version: V1.0
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "token";

    public CustomSessionManager() {
        super();
    }

    //重写需要加上Override
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        System.out.println("-----------------获取token 进行处理-----------------");
        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if (sessionId != null){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);

            //shiro会帮我们做校验，错了自动抛异常
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            //automatically mark it valid here.  If it is invalid, the
            //onUnknownSession method below will be invoked and we'll remove the attribute at that time.
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

            return sessionId;
        }else {
            return super.getSessionId(request,response);
        }

    }
}
