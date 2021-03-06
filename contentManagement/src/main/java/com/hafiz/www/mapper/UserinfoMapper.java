package com.hafiz.www.mapper;

import com.hafiz.www.po.Userinfo;

import java.util.List;

public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer u_id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer u_id);

    //根据用户名查询
    Userinfo selectByUname(String u_uname);

    List<Userinfo> selectByEmail(String u_email);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    List<Userinfo> getAllUsers(Userinfo userinfo);

}