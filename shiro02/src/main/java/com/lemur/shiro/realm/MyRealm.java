package com.lemur.shiro.realm;

import com.lemur.shiro.entity.User;
import com.lemur.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Component
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    //自定义授权方法:获取当前登录用户的角色、权限信息，返回给shiro用来进行授权认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("自定义授权方法");
        //1.获取用户身份信息
        String name = principalCollection.getPrimaryPrincipal().toString();

        //2.调用业务层获取用户的角色信息（数据库）
        List<String> roles = userService.getUserRoleInfo(name);
        System.out.println("当前用户角色信息=" + roles);

        //2.5调用业务层获取用户的权限信息（数据库）
        List<String> userPermissionInfo = userService.getUserPermissionInfo(name);
        System.out.println("当前用户权限信息 = " + userPermissionInfo);

        //3.创建对象，封装当前登录用户的角色、授权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //4.存储角色
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(userPermissionInfo);

        //5.返回信息
        return simpleAuthorizationInfo;
    }

    //自定义登录认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取用户身份信息
        String name = authenticationToken.getPrincipal().toString();
        //2.调用业务层获取用户信息（数据库）
        User user = userService.getUserInfoByName(name);
        //3.非空判断，将数据封装返回
        if (Objects.nonNull(user)) {
            return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), user.getPwd(), ByteSource.Util.bytes("salt"), name);
        }
        return null;
    }


}
