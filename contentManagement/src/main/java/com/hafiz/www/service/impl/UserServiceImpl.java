package com.hafiz.www.service.impl;

import com.hafiz.www.mapper.UserinfoMapper;
import com.hafiz.www.po.Userinfo;
import com.hafiz.www.service.UserService;
import com.hafiz.www.shiro.SessionUtils;
import com.hafiz.www.until.JsonResult;
import com.hafiz.www.until.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserinfoMapper mapper;
    /**
     * 保存用户基本信息
     * @param userinfo 用户实体
     * @return
     */
    public int insertSelective(Userinfo userinfo) {
        int id=0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        if(selectByUname(userinfo.getU_name())==null){
            if(selectByEmail(userinfo.getU_email()).size()>0){
                return -2;
            }else{
                try {
                    userinfo.setU_jurisdiction("customer");//系统注册默认为游客身份
                    userinfo.setU_create_time(df.format(new Date()));
                    userinfo.setU_status("1");//默认账户启动
                    userinfo.setU_password(MD5.SysMd5(userinfo));//密码加密
                    id = mapper.insertSelective(userinfo);
                }catch (Exception e){
                    System.out.println(e);
                    return -3;
                }
            }
        }else{
            return -1;
        }
        return id;
    }

    /**
     * 根据用户名查询相关用户基本信息
     * @param u_uname 用户名
     * @return userinfo 用户实体
     */
    public Userinfo selectByUname(String u_uname) {
        return mapper.selectByUname(u_uname);
    }
    /**
     * 根据邮箱查询相关用户基本信息
     * @param u_email 邮箱
     * @return userinfo 用户实体
     */
    public List<Userinfo> selectByEmail(String u_email) {
        List<Userinfo> list= new ArrayList <Userinfo>();
        list=mapper.selectByEmail(u_email);
        return list;
    }

    /**
     * 根据用户id查询相关信息
     * @param u_id 用户id
     * @return 用户实体
     */
    public Userinfo selectByPrimaryKey(Integer u_id) {
        return mapper.selectByPrimaryKey(u_id);
    }

    /**
     * 修改个人信息
     * @param record  个人信息实体
     * @return 1：修改成功；0修改失败
     */
    public int updateByPrimaryKeySelective(Userinfo record) {
        return mapper.updateByPrimaryKeySelective(record);
    }


    /**
     * 判断密码是否相同
     * @param old_password 旧密码
     * @return  true  or   false
     */
    public  boolean judgeThePassword(String old_password){
        //根据当前登陆用户id查询用户密码进行判定
        Boolean istrue = null;
        Userinfo userinfo = mapper.selectByPrimaryKey(SessionUtils.getLoginUserId());
        String password = userinfo.getU_password();//将数据库中的密码取出用于判断
        //将传入的密码进行加密
        userinfo.setU_password(old_password);
        old_password=new MD5().SysMd5(userinfo) ;
        if(old_password.equals(password)){
            istrue=true;
        }else{
            istrue=false;
        }
        return istrue;
    }

    /**
     * 获取用户
     * @param userinfo
     * @return
     */
    public JsonResult getAllUsers(Userinfo userinfo) {
        JsonResult jsonResult = new JsonResult();
        try {
            if(!SessionUtils.isLoggedIn()){
                jsonResult=jsonResult.newInstanceFail("请登陆系统再进行查询");
            }else{
                List<Userinfo> listUser = mapper.getAllUsers(userinfo);
                jsonResult=jsonResult.newInstanceSucess_d2(listUser);
            }

        }catch (Exception e){
            e.printStackTrace();
            jsonResult=jsonResult.newInstanceFail("查询失败");
        }
        return jsonResult;
    }

    /**
     * 删除用户
     * @param u_id  个人信息实体
     * @return
     */
    public int deleteUserinfoByManagemer(int u_id) {
        return mapper.deleteByPrimaryKey(u_id);
    }
}
