package com.hafiz.www.po;

public class UBlacklist {
    private Integer b_id;//数据id

    private String b_add_usrid;//添加人

    private String b_addtime;//添加时间

    private String b_computer_ip;//用户电脑ip

    private String b_uid;//黑名单用户id  如果为游客身份则记录为0；否则记录其用户id

    public Integer getB_id() {
        return b_id;
    }

    public void setB_id(Integer b_id) {
        this.b_id = b_id;
    }

    public String getB_add_usrid() {
        return b_add_usrid;
    }

    public void setB_add_usrid(String b_add_usrid) {
        this.b_add_usrid = b_add_usrid;
    }

    public String getB_addtime() {
        return b_addtime;
    }

    public void setB_addtime(String b_addtime) {
        this.b_addtime = b_addtime;
    }

    public String getB_computer_ip() {
        return b_computer_ip;
    }

    public void setB_computer_ip(String b_computer_ip) {
        this.b_computer_ip = b_computer_ip;
    }

    public String getB_uid() {
        return b_uid;
    }

    public void setB_uid(String b_uid) {
        this.b_uid = b_uid;
    }
}