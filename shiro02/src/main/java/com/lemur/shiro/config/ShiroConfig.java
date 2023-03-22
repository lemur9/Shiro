package com.lemur.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.lemur.shiro.realm.MyRealm;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ShiroConfig {

    @Resource
    private MyRealm myRealm;

    //1.配置SecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        //1.创建defaultWebSecurityManager 对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //2.创建加密对象，设置相关属性
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //2.1采用md5加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //2.2迭代加密的次数
        hashedCredentialsMatcher.setHashIterations(3);
        //3.将加密对象存储到myRealm中
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        //4.将myRealm存入defaultWebSecurityManager 对象
        defaultWebSecurityManager.setRealm(myRealm);
        //4.5设置rememberMe
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        //4.6设置缓存管理器
        defaultWebSecurityManager.setCacheManager(getEhcacheManager());
        //5.返回
        return defaultWebSecurityManager;
    }

    //2.配置shiro内置过滤器拦截范围
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
        //设置不认证可以访问的资源
        defaultShiroFilterChainDefinition.addPathDefinition("/myController/userLogin", "anon");
        defaultShiroFilterChainDefinition.addPathDefinition("/myController/login", "anon");
        //设置登出过滤器
        defaultShiroFilterChainDefinition.addPathDefinition("/logout", "logout");
        //设置需要进行登录认证的拦截范围
        defaultShiroFilterChainDefinition.addPathDefinition("/**", "authc");
        //添加存在用户的过滤器（rememberMe）
        defaultShiroFilterChainDefinition.addPathDefinition("/**", "user");
        return defaultShiroFilterChainDefinition;
    }






    //缓存管理器
    private EhCacheManager getEhcacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        InputStream is = null;
        try {
            is = ResourceUtils.getInputStreamForPath("classpath:ehcache/ehcache-shiro.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CacheManager cacheManager = new CacheManager(is);
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }

    //cookie 属性设置
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //设置跨域
        //cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        return cookie;
    }

    //创建Shiro的cookie管理对象
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("1234567890987654".getBytes());
        return cookieRememberMeManager;
    }


    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
