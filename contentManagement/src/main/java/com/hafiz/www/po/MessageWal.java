package com.hafiz.www.po;

import javax.persistence.Transient;

/**
 * 留言墙表
 */
public class MessageWal {
    private Integer w_id;//数据id

    private Integer w_uid;//发表留言人用户id

    private String w_praisenumber;//点赞数

    private String w_status;//数据状态

    private String w_addtime;//添加时间

    private String w_content;//发布内容
    @Transient
    private Userinfo userinfo;//用户信息实体，用于关联查询
    @Transient
    private  MessageRemark message_remark;//留言数据评论表
    @Transient
    private String count_remark;//评论条数

    public String getCount_remark() {
        return count_remark;
    }

    public void setCount_remark(String count_remark) {
        this.count_remark = count_remark;
    }

    public MessageRemark getMessageRemark() {
        return message_remark;
    }

    public void setMessageRemark(MessageRemark message_remark) {
        this.message_remark = message_remark;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public Integer getW_id() {
        return w_id;
    }

    public void setW_id(Integer w_id) {
        this.w_id = w_id;
    }

    public Integer getW_uid() {
        return w_uid;
    }

    public void setW_uid(Integer w_uid) {
        this.w_uid = w_uid;
    }

    public String getW_praisenumber() {
        return w_praisenumber;
    }

    public void setW_praisenumber(String w_praisenumber) {
        this.w_praisenumber = w_praisenumber;
    }

    public String getW_status() {
        return w_status;
    }

    public void setW_status(String w_status) {
        this.w_status = w_status;
    }

    public String getW_addtime() {
        return w_addtime;
    }

    public void setW_addtime(String w_addtime) {
        this.w_addtime = w_addtime;
    }



    public String getW_content() {
        return w_content;
    }

    public void setW_content(String w_content) {
        this.w_content = w_content;
    }
}