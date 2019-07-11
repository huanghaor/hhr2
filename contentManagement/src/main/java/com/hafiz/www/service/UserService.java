package com.hafiz.www.service;

import com.hafiz.www.po.Userinfo;
import com.hafiz.www.until.JsonResult;

import java.util.List;

/**
 * Desc:用户表相关的service接口
 * Created by hafiz.zhang on 2016/8/27.
 */
public interface UserService {

    /**
     * 保存用户信息
     * @param userinfo
     * @return
     */
    int insertSelective(Userinfo userinfo);

    /**
     * 根据用户名查询用户信息
     * @param u_uname
     * @return
     */
    Userinfo selectByUname(String u_uname);

    /**
     * 根据邮箱查询用户信息
     * @param u_email
     * @return
     */
    List<Userinfo> selectByEmail(String u_email);

    /**
     * 根据用户id查询相关信息
     * @param u_id 用户id
     * @return 用户实体
     */
    Userinfo selectByPrimaryKey(Integer u_id);

    /**
     * 修改个人信息
     * @param record  个人信息实体
     * @return  1：修改成功；0修改失败
     */
    int updateByPrimaryKeySelective(Userinfo record);

    /**
     * 修改个人信息
     * @param u_id  个人信息实体
     * @return  1：修改成功；0修改失败
     */
    int deleteUserinfoByManagemer(int u_id);

    /**
     * 判断密码是否相同
     * @param old_password 旧密码
     * @return  true  or   false
     */
    boolean judgeThePassword(String old_password);

    /**
     * 获取用户
     * @param userinfo
     * @return
     */
    JsonResult getAllUsers(Userinfo userinfo);
}
