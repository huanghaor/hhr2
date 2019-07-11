package com.hafiz.www.po;

import javax.persistence.Transient;

/**
 * 用户信息表
 */
public class Userinfo {
    private Integer u_id;//用户id

    private String u_name;//账号

    private String u_password;//密码

    private String u_phone;//联系电话

    private String u_create_time;//注册时间

    private String u_truename;//系统昵称

    private String u_status;//当前用户状态

    private String u_remarks;//备注

    private String u_userhead;//用户头像url

    private String u_email;//用户邮箱

    private String u_jurisdiction;//用户权限:admin;customer

    public String getU_jurisdiction() {
        return u_jurisdiction;
    }

    public void setU_jurisdiction(String u_jurisdiction) {
        this.u_jurisdiction = u_jurisdiction;
    }

    @Transient
    private  String code;//验证码

    @Transient
    private  String old_password;//密码：该字段用于修改密码时，上传的旧密码用于判断

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_create_time() {
        return u_create_time;
    }

    public void setU_create_time(String u_create_time) {
        this.u_create_time = u_create_time;
    }

    public String getU_truename() {
        return u_truename;
    }

    public void setU_truename(String u_truename) {
        this.u_truename = u_truename;
    }

    public String getU_status() {
        return u_status;
    }

    public void setU_status(String u_status) {
        this.u_status = u_status;
    }

    public String getU_remarks() {
        return u_remarks;
    }

    public void setU_remarks(String u_remarks) {
        this.u_remarks = u_remarks;
    }

    public String getU_userhead() {
        return u_userhead;
    }

    public void setU_userhead(String u_userhead) {
        this.u_userhead = u_userhead;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }
}