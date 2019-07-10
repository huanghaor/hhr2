package com.hhr.springbootstudy.service;

import com.hhr.springbootstudy.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoSerivce {

    UserInfo findByname(@Param("name") String name);

    int addUserInfo(UserInfo userInfo);
}
