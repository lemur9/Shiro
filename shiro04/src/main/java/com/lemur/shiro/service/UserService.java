package com.lemur.shiro.service;

import com.lemur.shiro.entity.User;

import java.util.List;

public interface UserService {
    //用户登录
    User getUserInfoByName(String name);

    //根据用户查询角色信息
    List<String> getUserRoleInfo(String principal);

    //获取用户角色权限信息
    List<String> getUserPermissionInfo(String principal);
}
