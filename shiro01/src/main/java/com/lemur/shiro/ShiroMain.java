package com.lemur.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class ShiroMain {
    public static void main(String[] args) {
        //1.初始化获取securityManager认证管理器
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //2.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        //3.创建Token对象，web应用用户名密码从页面传递
        AuthenticationToken token = new UsernamePasswordToken("zhangsan", "z3");
        //4.完成登录
        try {
            subject.login(token);
            System.out.println("登录成功");
            //5.判断角色
            boolean role1 = subject.hasRole("role1");
            System.out.println("是否拥有此角色：" + role1);
            //6.判断权限
            boolean permitted = subject.isPermitted("user:insert");
            System.out.println("是否拥有此权限：" + permitted);
            //也可以用checkPermission方法，但是没有返回值，没权限抛UnauthorizedException
            subject.checkPermission("user:select1");

        } catch (UnknownAccountException e) {
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}
