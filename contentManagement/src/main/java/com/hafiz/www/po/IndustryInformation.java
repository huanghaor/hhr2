package com.hafiz.www.po;

import javax.persistence.Transient;

/**
 * 行业资讯
 */
public class IndustryInformation {
    private Integer i_id;

    private String i_uid;//发表人id

    private String i_readnumber;//阅读量

    private String i_addtime;//添加时间

    private String i_title;//资讯标题

    private String i_type;//资讯类别

    private String i_resources_mode;//资讯产生方式：原创——1；转载——2；

    private String i_resources_mode_url;//资讯资源转载路径

    private String i_status;//资讯状态

    private String i_fine_paste;//设置精帖：1是；0否

    private String i_content;//资讯内容

    private String i_key_words;//关键词

    public String getI_key_words() {
        return i_key_words;
    }

    public void setI_key_words(String i_key_words) {
        this.i_key_words = i_key_words;
    }

    @Transient
    private Userinfo userinfo;//用户信息表

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public Integer getI_id() {
        return i_id;
    }

    public void setI_id(Integer i_id) {
        this.i_id = i_id;
    }

    public String getI_uid() {
        return i_uid;
    }

    public void setI_uid(String i_uid) {
        this.i_uid = i_uid;
    }

    public String getI_readnumber() {
        return i_readnumber;
    }

    public void setI_readnumber(String i_readnumber) {
        this.i_readnumber = i_readnumber;
    }

    public String getI_addtime() {
        return i_addtime;
    }

    public void setI_addtime(String i_addtime) {
        this.i_addtime = i_addtime;
    }

    public String getI_title() {
        return i_title;
    }

    public void setI_title(String i_title) {
        this.i_title = i_title;
    }

    public String getI_type() {
        return i_type;
    }

    public void setI_type(String i_type) {
        this.i_type = i_type;
    }

    public String getI_resources_mode() {
        return i_resources_mode;
    }

    public void setI_resources_mode(String i_resources_mode) {
        this.i_resources_mode = i_resources_mode;
    }

    public String getI_resources_mode_url() {
        return i_resources_mode_url;
    }

    public void setI_resources_mode_url(String i_resources_mode_url) {
        this.i_resources_mode_url = i_resources_mode_url;
    }

    public String getI_status() {
        return i_status;
    }

    public void setI_status(String i_status) {
        this.i_status = i_status;
    }

    public String getI_fine_paste() {
        return i_fine_paste;
    }

    public void setI_fine_paste(String i_fine_paste) {
        this.i_fine_paste = i_fine_paste;
    }

    public String getI_content() {
        return i_content;
    }

    public void setI_content(String i_content) {
        this.i_content = i_content;
    }
}