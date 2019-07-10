package com.hhr.springbootstudy.controller;

import com.hhr.springbootstudy.entity.UserInfo;
import com.hhr.springbootstudy.service.UserInfoSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoSerivce userInfoSerivce;


    @RequestMapping("insertUser")
    public int insertUser(UserInfo userInfo){
        return userInfoSerivce.addUserInfo(userInfo);
    }

    @RequestMapping("findtUser")
    public UserInfo findtUser(String name){
        return userInfoSerivce.findByname(name);
    }
}
