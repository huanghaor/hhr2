package com.hafiz.www.po;
import javax.persistence.Transient;
public class EssayDiary {
    private Integer e_id;

    private Integer e_uid;

    private Integer e_readnumber;

    private String e_status;

    private String e_addtime;

    private String e_type;

    private String e_title;

    private String e_url;

    private String e_content;

    private String e_resources_mode;//随笔文章产生方式：原创——1；转载——2；

    private String e_resources_mode_url;//资源转载路径

    private String e_fine_paste;//是否设置为精贴：1：是；0：否

    public String getE_fine_paste() {
        return e_fine_paste;
    }

    public void setE_fine_paste(String e_fine_paste) {
        this.e_fine_paste = e_fine_paste;
    }

    public String getE_resources_mode() {
        return e_resources_mode;
    }

    public void setE_resources_mode(String e_resources_mode) {
        this.e_resources_mode = e_resources_mode;
    }

    public String getE_resources_mode_url() {
        return e_resources_mode_url;
    }

    public void setE_resources_mode_url(String e_resources_mode_url) {
        this.e_resources_mode_url = e_resources_mode_url;
    }

    @Transient
    private Userinfo userinfo;

    @Transient
    private EssayDiaryType essay_diary_type;

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public EssayDiaryType getEssay_diary_type() {
        return essay_diary_type;
    }

    public void setEssay_diary_type(EssayDiaryType essay_diary_type) {
        this.essay_diary_type = essay_diary_type;
    }

    public Integer getE_id() {
        return e_id;
    }

    public void setE_id(Integer e_id) {
        this.e_id = e_id;
    }

    public Integer getE_uid() {
        return e_uid;
    }

    public void setE_uid(Integer e_uid) {
        this.e_uid = e_uid;
    }

    public Integer getE_readnumber() {
        return e_readnumber;
    }

    public void setE_readnumber(Integer e_readnumber) {
        this.e_readnumber = e_readnumber;
    }

    public String getE_status() {
        return e_status;
    }

    public void setE_status(String e_status) {
        this.e_status = e_status;
    }

    public String getE_addtime() {
        return e_addtime;
    }

    public void setE_addtime(String e_addtime) {
        this.e_addtime = e_addtime;
    }

    public String getE_type() {
        return e_type;
    }

    public void setE_type(String e_type) {
        this.e_type = e_type;
    }

    public String getE_title() {
        return e_title;
    }

    public void setE_title(String e_title) {
        this.e_title = e_title;
    }

    public String getE_url() {
        return e_url;
    }

    public void setE_url(String e_url) {
        this.e_url = e_url;
    }

    public String getE_content() {
        return e_content;
    }

    public void setE_content(String e_content) {
        this.e_content = e_content;
    }
}