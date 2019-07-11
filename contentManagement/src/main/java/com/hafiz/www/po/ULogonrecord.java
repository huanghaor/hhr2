package com.hafiz.www.po;

public class ULogonrecord {
    private Integer r_id;//数据id

    private Integer u_id;//用户id

    private String u_logintime;//用户登录时间

    private String u_logouttime;//登出时间

    private String u_computer_ip;//用户电脑ip

    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_logintime() {
        return u_logintime;
    }

    public void setU_logintime(String u_logintime) {
        this.u_logintime = u_logintime;
    }

    public String getU_logouttime() {
        return u_logouttime;
    }

    public void setU_logouttime(String u_logouttime) {
        this.u_logouttime = u_logouttime;
    }

    public String getU_computer_ip() {
        return u_computer_ip;
    }

    public void setU_computer_ip(String u_computer_ip) {
        this.u_computer_ip = u_computer_ip;
    }
}