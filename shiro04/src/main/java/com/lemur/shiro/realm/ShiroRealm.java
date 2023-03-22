package com.lemur.shiro.realm;

import com.lemur.shiro.entity.User;
import com.lemur.shiro.mapper.UserMapper;
import com.lemur.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Description: 用户登录鉴权和获取用户授权
 * @Author: Scott
 * @Date: 2019-4-23 8:13
 * @Version: 1.1
 */
@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    /**
     * 权限信息认证(包括角色以及权限)是用户访问controller的时候才进行验证(redis存储的此处权限信息)
     * 触发检测用户权限时才会调用此方法，例如checkRole,checkPermission
     *
     * @param principals 身份信息
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("===============Shiro权限认证开始============ [ roles、permissions]==========");
        String username = null;
        if (principals != null) {
            User sysUser = (User) principals.getPrimaryPrincipal();
            username = sysUser.getName();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 设置用户拥有的角色集合，比如“admin,test”
        Set<String> roleSet = new HashSet<>(userMapper.getUserRoleInfoMapper(username));
        System.out.println(roleSet);
        info.setRoles(roleSet);

        // 设置用户拥有的权限集合，比如“sys:role:add,sys:user:add”
        Set<String> permissionSet = new HashSet<>(userMapper.getUserPermissionInfoMapper(username));
        info.addStringPermissions(permissionSet);
        System.out.println(permissionSet);
        log.info("===============Shiro权限认证成功==============");
        return info;
    }

    /**
     * 用户信息认证是在用户进行登录的时候进行验证(不存redis)
     * 也就是说验证用户输入的账号和密码是否正确，错误抛出异常
     *
     * @param auth 用户登录的账号密码信息
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) {
        log.debug("===============Shiro身份认证开始============doGetAuthenticationInfo==========");

        //1.获取用户身份信息
        String name = auth.getPrincipal().toString();
        //2.调用业务层获取用户信息（数据库）
        User user = userService.getUserInfoByName(name);
        //3.非空判断，将数据封装返回
        if (Objects.nonNull(user)) {
            return new SimpleAuthenticationInfo(auth.getPrincipal(), user.getPwd(), ByteSource.Util.bytes("salt"), name);
        }
        return null;
    }

    /**
     * 清除当前用户的权限认证缓存
     *
     * @param principals 权限信息
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
}
