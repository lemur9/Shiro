package com.lemur.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemur.shiro.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select name from role where id in (select rid from role_user where uid = (select id from user where name = #{principal}))")
    List<String> getUserRoleInfoMapper(@Param("principal") String principal);

    @Select("select info from permissions where id in (select pid from role_ps where rid in (select id from role_user where uid = (select id from user where name = #{principal})))")
    List<String> getUserPermissionInfoMapper(@Param("principal") String principal);

    @Select("select * from user where name = #{principal}")
    User getUserByName(@Param("principal") String name);
}
