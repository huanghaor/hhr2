package com.hafiz.www.po;

import javax.persistence.Transient;

/**
 * 留言墙评论回复
 */
public class MessageRemarkReply {
    private Integer reply_id;//回复数据id

    private Integer reply_from_uid;//评论人id

    private Integer reply_to_uid;//被评论人id

    private Integer remark_id;//评论数据id

    private Integer reply_praisenumber;//评论点赞数

    private String reply_statu;//状态

    private String reply_add_time;//回复书简

    private Integer reply_sameid_with_two_person;

    private Integer reply_addid_with_two_person;

    private String reply_content;//回复内容

    @Transient
    private String from_truename;//评论人昵称

    @Transient
    private String to_truename;//被评论人昵称

    public String getFrom_truename() {
        return from_truename;
    }

    public void setFrom_truename(String from_truename) {
        this.from_truename = from_truename;
    }

    public String getTo_truename() {
        return to_truename;
    }

    public void setTo_truename(String to_truename) {
        this.to_truename = to_truename;
    }

    @Transient
    private Userinfo userinfo;//用户信息实体，用于关联查询

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public Integer getReply_id() {
        return reply_id;
    }

    public void setReply_id(Integer reply_id) {
        this.reply_id = reply_id;
    }

    public Integer getReply_from_uid() {
        return reply_from_uid;
    }

    public void setReply_from_uid(Integer reply_from_uid) {
        this.reply_from_uid = reply_from_uid;
    }

    public Integer getReply_to_uid() {
        return reply_to_uid;
    }

    public void setReply_to_uid(Integer reply_to_uid) {
        this.reply_to_uid = reply_to_uid;
    }

    public Integer getRemark_id() {
        return remark_id;
    }

    public void setRemark_id(Integer remark_id) {
        this.remark_id = remark_id;
    }

    public Integer getReply_praisenumber() {
        return reply_praisenumber;
    }

    public void setReply_praisenumber(Integer reply_praisenumber) {
        this.reply_praisenumber = reply_praisenumber;
    }

    public String getReply_statu() {
        return reply_statu;
    }

    public void setReply_statu(String reply_statu) {
        this.reply_statu = reply_statu;
    }

    public String getReply_add_time() {
        return reply_add_time;
    }

    public void setReply_add_time(String reply_add_time) {
        this.reply_add_time = reply_add_time;
    }

    public Integer getReply_sameid_with_two_person() {
        return reply_sameid_with_two_person;
    }

    public void setReply_sameid_with_two_person(Integer reply_sameid_with_two_person) {
        this.reply_sameid_with_two_person = reply_sameid_with_two_person;
    }

    public Integer getReply_addid_with_two_person() {
        return reply_addid_with_two_person;
    }

    public void setReply_addid_with_two_person(Integer reply_addid_with_two_person) {
        this.reply_addid_with_two_person = reply_addid_with_two_person;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }
}