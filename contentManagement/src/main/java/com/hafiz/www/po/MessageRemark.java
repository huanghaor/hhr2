package com.hafiz.www.po;

import javax.persistence.Transient;
import java.util.List;

/**
 * 留言墙评论
 */
public class MessageRemark {
    private Integer remark_id;//数据id

    private Integer remark_from_uid;//评论人id

    private Integer remark_message_id;//留言数据id

    private Integer remark_praisenumber;//留言评论数据点赞

    private String remark_statu;//留言评论数据状态

    private String remark_add_time;//评论添加时间

    private String remark_content;//评论内容
    @Transient
    private Userinfo userinfo;//用户信息实体，用于关联查询
    @Transient
    private List<MessageRemarkReply>  messageRemarkReply;//关联查询评论回复对话

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }
    public List <MessageRemarkReply> getMessageRemarkReply() {
        return messageRemarkReply;
    }

    public void setMessageRemarkReply(List <MessageRemarkReply> messageRemarkReply) {
        this.messageRemarkReply = messageRemarkReply;
    }

    public Integer getRemark_id() {
        return remark_id;
    }

    public void setRemark_id(Integer remark_id) {
        this.remark_id = remark_id;
    }

    public Integer getRemark_from_uid() {
        return remark_from_uid;
    }

    public void setRemark_from_uid(Integer remark_from_uid) {
        this.remark_from_uid = remark_from_uid;
    }

    public Integer getRemark_message_id() {
        return remark_message_id;
    }

    public void setRemark_message_id(Integer remark_message_id) {
        this.remark_message_id = remark_message_id;
    }

    public Integer getRemark_praisenumber() {
        return remark_praisenumber;
    }

    public void setRemark_praisenumber(Integer remark_praisenumber) {
        this.remark_praisenumber = remark_praisenumber;
    }

    public String getRemark_statu() {
        return remark_statu;
    }

    public void setRemark_statu(String remark_statu) {
        this.remark_statu = remark_statu;
    }

    public String getRemark_add_time() {
        return remark_add_time;
    }

    public void setRemark_add_time(String remark_add_time) {
        this.remark_add_time = remark_add_time;
    }

    public String getRemark_content() {
        return remark_content;
    }

    public void setRemark_content(String remark_content) {
        this.remark_content = remark_content;
    }
}