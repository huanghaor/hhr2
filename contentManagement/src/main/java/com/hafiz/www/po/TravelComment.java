package com.hafiz.www.po;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * 游记评论
 */
public class TravelComment {
    private Integer c_id;//评论数据id

    private String tc_id;//游记id

    private String c_comment_content;//评论内容

    private Integer c_commenttator_id;//评论人id

    private String c_comment_time;//评论时间

    private Integer c_praisenumber;//点赞数

    private String c_statu;//数据状态
    @Transient
    private Userinfo userinfo;//用户信息实体，用于关联查询
    @Transient
    private List<TravelCommentReply> travel_comment_reply;


    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public List <TravelCommentReply> getTravel_comment_reply() {
        return travel_comment_reply;
    }

    public void setTravel_comment_reply(List <TravelCommentReply> travel_comment_reply) {
        this.travel_comment_reply = travel_comment_reply;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getTc_id() {
        return tc_id;
    }

    public void setTc_id(String tc_id) {
        this.tc_id = tc_id;
    }

    public String getC_comment_content() {
        return c_comment_content;
    }

    public void setC_comment_content(String c_comment_content) {
        this.c_comment_content = c_comment_content;
    }

    public Integer getC_commenttator_id() {
        return c_commenttator_id;
    }

    public void setC_commenttator_id(Integer c_commenttator_id) {
        this.c_commenttator_id = c_commenttator_id;
    }

    public String getC_comment_time() {
        return c_comment_time;
    }

    public void setC_comment_time(String c_comment_time) {
        this.c_comment_time = c_comment_time;
    }

    public Integer getC_praisenumber() {
        return c_praisenumber;
    }

    public void setC_praisenumber(Integer c_praisenumber) {
        this.c_praisenumber = c_praisenumber;
    }

    public String getC_statu() {
        return c_statu;
    }

    public void setC_statu(String c_statu) {
        this.c_statu = c_statu;
    }
}