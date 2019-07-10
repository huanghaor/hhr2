package com.hhr.springbootstudy.mapper;

import com.hhr.springbootstudy.entity.User;
import com.hhr.springbootstudy.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserInfoMapper {
    @Select("select id,name,age from userinfo where name=#{name}")
    UserInfo findByname(@Param("name") String name);

    @Insert("insert into userinfo(name,age) values (#{name},#{age})")
    int addUserInfo(UserInfo userInfo);
}
