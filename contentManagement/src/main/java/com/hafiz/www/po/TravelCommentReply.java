package com.hafiz.www.po;

import javax.persistence.Transient;

/**
 * 游记评论回复
 */
public class TravelCommentReply {
    private Integer reply_id;//回复数据id

    private Integer reply_from_uid;//回复人id

    private Integer reply_to_uid;//被回复人id

    private Integer replyc_id;//评论数据id

    private String reply_praisenumber;//点赞数

    private String reply_statu;//数据状态

    private String reply_add_time;//数据添加时间

    private String reply_sameid_with_two_person;//两人对话的id,标记为两个的对话：即：a与b中的所有对话记录id为1；a与c的所有对话记录为2；b与c的对话记录为3；依次累计

    private String reply_addid_with_two_person;//记录两人间对话的次数，累加

    private String reply_content;//回复内容

    @Transient
    private String from_truename;//回复人昵称

    @Transient
    private String to_truename;//被回复人昵称

   @Transient
    private Userinfo userinfo;//用户信息实体，用于关联查询

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

    public Integer getReplyc_id() {
        return replyc_id;
    }

    public void setReplyc_id(Integer replyc_id) {
        this.replyc_id = replyc_id;
    }

    public String getReply_praisenumber() {
        return reply_praisenumber;
    }

    public void setReply_praisenumber(String reply_praisenumber) {
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

    public String getReply_sameid_with_two_person() {
        return reply_sameid_with_two_person;
    }

    public void setReply_sameid_with_two_person(String reply_sameid_with_two_person) {
        this.reply_sameid_with_two_person = reply_sameid_with_two_person;
    }

    public String getReply_addid_with_two_person() {
        return reply_addid_with_two_person;
    }

    public void setReply_addid_with_two_person(String reply_addid_with_two_person) {
        this.reply_addid_with_two_person = reply_addid_with_two_person;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }
}