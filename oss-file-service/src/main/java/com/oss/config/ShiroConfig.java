package com.oss.config;



import com.oss.tool.shiro.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName：ShiroConfig
 * @Description: shiro配置
 * @Author：13738700108
 * @Data 2020/2/17 16:54
 * @Version: v1.0
 **/
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

        System.out.println("---------------ShiroFilterFactoryBean.shiroFilter()-----------");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //需要登录的接口如果访问某个接口需要登录却没有登录则调用次接口（如果不是前后端分离,则跳转页面)
//        shiroFilterFactoryBean.setLoginUrl("/pub/need_login");
        shiroFilterFactoryBean.setLoginUrl("/login/login");

        //登录成功调转url，如果前后端分离则没有这个调用
//        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setSuccessUrl("/home");

        //没有权限，未授权就会调用此方法，先验证登录-> 再验证是否有权限
//        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permit");
        shiroFilterFactoryBean.setUnauthorizedUrl("/login/textRedis");

        //设置自定义filter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("roleOrFilter",new CustomRolesOrAuthorizationFilter());       //失效  原因未知
        shiroFilterFactoryBean.setFilters(filterMap);

        //拦截器路径， 坑一:部分路径无法进行拦截，时有时无，因为使用了hashMap，无序的，应该改为LinkedHashMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //退出过滤器
        filterChainDefinitionMap.put("/logout","logout");

        //配置记住我或认证通过可以访问的地址
//        filterChainDefinitionMap.put("/", "user");

        //匿名可以访问，也就是游客模式
        filterChainDefinitionMap.put("/pub/**","anon");
        filterChainDefinitionMap.put("/topic/**","anon");
        filterChainDefinitionMap.put("/app/**","anon");
        filterChainDefinitionMap.put("/login/sendSms","anon");
        filterChainDefinitionMap.put("/login/register","anon");
        filterChainDefinitionMap.put("/login/textRedis","anon");

        //登录用户才可以访问
        filterChainDefinitionMap.put("/authc/**","authc");
//        filterChainDefinitionMap.put("/role/**","authc");

        //管理员角色才可以访问
//        filterChainDefinitionMap.put("/admin/**","roles[admin]");
//        filterChainDefinitionMap.put("/role/**","roles[admin]");

        //有编辑权限的才可以访问
//        filterChainDefinitionMap.put("/video/update","perms[video_update]");
        filterChainDefinitionMap.put("/zone/addZone","perms[addZone]");
        filterChainDefinitionMap.put("/zone/deleteZoneByIds","perms[deleteZoneByIds]");
        filterChainDefinitionMap.put("/role/allotRole","perms[allotRole]");
        filterChainDefinitionMap.put("/role/allotZone","perms[allotZone]");
        filterChainDefinitionMap.put("/file/uploadFile","perms[uploadFile]");
        filterChainDefinitionMap.put("/file/delPrefixFile","perms[delPrefixFile]");

        //坑二：过滤链是顺序执行，从上而下，一般讲/** 放在最下面

        //authc，rul定义必须通过认真才可以访问
        //authc, url可以匿名访问
        filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;



    }

    @Bean
    public SecurityManager  securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();


        //如果不是前后端分离，则不必设置下面的sessionManager
        securityManager.setSessionManager(sessionManager());

        //使用自定义的cacheManager
        securityManager.setCacheManager(cacheManager());

//        //使用记住我的
//        securityManager.setRememberMeManager(rememberMeManager());

        //设置realm（推荐放到最后，某些情况会不生效）
        securityManager.setRealm(customRealm());
        return  securityManager;
    }

//    /**
//     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
//     */
//    @Bean
//    public CookieRememberMeManager rememberMeManager() {
//        CookieRememberMeManager manager = new CookieRememberMeManager();
////        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
//        manager.setCipherKey("12321312".getBytes());
//        manager.setCookie( rememberMeCookie());
//        return manager;
//    }
//
//    /**
//     * 记住密码Cookie
//     */
//    @Bean
//    public SimpleCookie rememberMeCookie() {
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
//        return simpleCookie;
//    }



    /**
     * 配置具体实现类
     * @return
     */
    public RedisCacheManager cacheManager(){
        RedisCacheManager  redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        //设置过期时间，单位是秒，
        redisCacheManager.setExpire(60*60*24);
        return redisCacheManager;
    }

    /**
     * 配置redisManager
     */
    public RedisManager getRedisManager(){
        RedisManager redisManager = new RedisManager();

        redisManager.setHost("127.0.0.1");
        redisManager.setPort(6379);
        redisManager.setPassword("123456");
        redisManager.setDatabase(15);
        return redisManager;
    }


    /**
     * 自定义realm
     * @return
     */
    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    /**
     * 密码加解密规则
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        //设置散列算法 ，这里使用的是MD5
        hashedCredentialsMatcher.setHashAlgorithmName("md5");

        //散列次数，好比散列2次
//        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();

        //超时时间,默认30分钟，会话超时,方法里面的单位是毫秒
//        customSessionManager.setGlobalSessionTimeout(1000*60*60*24);
        customSessionManager.setGlobalSessionTimeout(1000*60);
        //配置session持久化
        customSessionManager.setSessionDAO(redisSessionDAO());
        return  customSessionManager;
    }

    /**
     * 自定义session持久化
     * @return
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO  = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());

        //设置sessionid生成器
        redisSessionDAO.setSessionIdGenerator(new CustomSessionIdGenerator());

        return redisSessionDAO;
    }

    /**
     * 管理shiro一些beand的生命周期 即bean的初始化与销毁
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * api controller 层面
     * 加入注解的使用，不加入这个AOP注解不生效（shiro的注解 例如 @RequiresGuest）
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 作用: 用来扫描上下文寻找所有的Advistor(通知器),
     * 将符合条件的Advisor应用到切入点的Bean中，需要在LifecycleBeanPostProcessor创建后才可以创建
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
