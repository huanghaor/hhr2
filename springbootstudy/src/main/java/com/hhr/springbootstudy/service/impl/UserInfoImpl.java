package com.hhr.springbootstudy.service.impl;

import com.hhr.springbootstudy.entity.UserInfo;
import com.hhr.springbootstudy.mapper.UserInfoMapper;
import com.hhr.springbootstudy.service.UserInfoSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserInfoImpl implements UserInfoSerivce {


    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo findByname(String name) {
        UserInfo userInfo = userInfoMapper.findByname(name);
        log.info("findByname#######{}:"+userInfo);
        return userInfo;
    }

    @Transactional
    public int addUserInfo(UserInfo userInfo) {
        int data = userInfoMapper.addUserInfo(userInfo);
        int i= 1 / userInfo.getAge();
        log.info("addUserInfo#######{}:"+data);
        return data;
    }
}
