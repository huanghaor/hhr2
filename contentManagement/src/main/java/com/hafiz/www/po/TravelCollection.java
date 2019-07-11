package com.hafiz.www.po;

import javax.persistence.Transient;
import java.util.List;

/**
 * 驴行游记
 */
public class TravelCollection {
    private Integer t_id;//数据id

    private Integer u_id;//发布人id

    private String t_title;//游记标题

    private String t_place;//出行目的地

    private String t_remarks;//备注

    private Integer t_praisenumber;//点赞数

    private String t_create_time;//创建晒你

    private String t_travelperson;//出行人数

    private String t_per_capita_consumption;//人均消费

    private String t_begin_time;//出行开始时间

    private String t_end_time;//出行结束时间

    private String t_tourism_strategy;//游记内容

    private String t_fine_paste;//是否设置为精贴：1是；0否

    public String getT_fine_paste() {
        return t_fine_paste;
    }

    public void setT_fine_paste(String t_fine_paste) {
        this.t_fine_paste = t_fine_paste;
    }

    @Transient
    private String fileList;

    @Transient
    private List<TravelPictureUrl> travel_picture_url;

    @Transient
    private  Userinfo userinfo;

    @Transient
    private String travel_comment;//评论数

    public String getTravel_comment() {
        return travel_comment;
    }

    public void setTravel_comment(String travel_comment) {
        this.travel_comment = travel_comment;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public List <TravelPictureUrl> getTravel_picture_url() {
        return travel_picture_url;
    }

    public void setTravel_picture_url(List <TravelPictureUrl> travel_picture_url) {
        this.travel_picture_url = travel_picture_url;
    }

    public String getFileList() {
        return fileList;
    }

    public void setFileList(String fileList) {
        this.fileList = fileList;
    }

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getT_title() {
        return t_title;
    }

    public void setT_title(String t_title) {
        this.t_title = t_title;
    }

    public String getT_place() {
        return t_place;
    }

    public void setT_place(String t_place) {
        this.t_place = t_place;
    }

    public String getT_remarks() {
        return t_remarks;
    }

    public void setT_remarks(String t_remarks) {
        this.t_remarks = t_remarks;
    }

    public Integer getT_praisenumber() {
        return t_praisenumber;
    }

    public void setT_praisenumber(Integer t_praisenumber) {
        this.t_praisenumber = t_praisenumber;
    }

    public String getT_create_time() {
        return t_create_time;
    }

    public void setT_create_time(String t_create_time) {
        this.t_create_time = t_create_time;
    }

    public String getT_travelperson() {
        return t_travelperson;
    }

    public void setT_travelperson(String t_travelperson) {
        this.t_travelperson = t_travelperson;
    }

    public String getT_per_capita_consumption() {
        return t_per_capita_consumption;
    }

    public void setT_per_capita_consumption(String t_per_capita_consumption) {
        this.t_per_capita_consumption = t_per_capita_consumption;
    }

    public String getT_begin_time() {
        return t_begin_time;
    }

    public void setT_begin_time(String t_begin_time) {
        this.t_begin_time = t_begin_time;
    }

    public String getT_end_time() {
        return t_end_time;
    }

    public void setT_end_time(String t_end_time) {
        this.t_end_time = t_end_time;
    }

    public String getT_tourism_strategy() {
        return t_tourism_strategy;
    }

    public void setT_tourism_strategy(String t_tourism_strategy) {
        this.t_tourism_strategy = t_tourism_strategy;
    }
}