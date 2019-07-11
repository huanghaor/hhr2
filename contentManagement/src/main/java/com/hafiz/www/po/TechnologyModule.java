package com.hafiz.www.po;

import javax.persistence.Transient;
import java.util.List;

/**
 * 技术文章
 */
public class TechnologyModule {
    private Integer m_id;//数据id

    private Integer m_publish_personid;//发布人id

    private Integer m_typeid;//文章类型id

    private String m_title;//文章标题

    private String m_datatime;//添加时间

    private Integer m_readnum;//阅读量

    private String m_content;//文章内容

    private String m_resources_mode;//资源获取方式：原创——1；转载——2

    private String m_resources_mode_url;//资源转载路径

    private String m_fine_paste;//文章推荐 ：1:推荐；2不推荐

    public String getM_fine_paste() {
        return m_fine_paste;
    }

    public void setM_fine_paste(String m_fine_paste) {
        this.m_fine_paste = m_fine_paste;
    }

    public String getM_resources_mode() {
        return m_resources_mode;
    }

    public void setM_resources_mode(String m_resources_mode) {
        this.m_resources_mode = m_resources_mode;
    }

    public String getM_resources_mode_url() {
        return m_resources_mode_url;
    }

    public void setM_resources_mode_url(String m_resources_mode_url) {
        this.m_resources_mode_url = m_resources_mode_url;
    }

    @Transient
    private String fileList;//关联查询该文章保存附件的路径list

    @Transient
    private List<TechnologyModuleEnclosure> technology_module_enclosure;

    @Transient
    private TypeOfArticle type_of_article;

    @Transient
    private Userinfo userinfo;

    @Transient
    private String ReprintUrl;//转载的url

    public String getReprintUrl() {

        return ReprintUrl;
    }

    public void setReprintUrl(String reprintUrl) {
        ReprintUrl = reprintUrl;
    }

    public List <TechnologyModuleEnclosure> getTechnology_module_enclosure() {
        return technology_module_enclosure;
    }

    public void setTechnology_module_enclosure(List <TechnologyModuleEnclosure> technology_module_enclosure) {
        this.technology_module_enclosure = technology_module_enclosure;
    }

    public TypeOfArticle getType_of_article() {
        return type_of_article;
    }

    public void setType_of_article(TypeOfArticle type_of_article) {
        this.type_of_article = type_of_article;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public String getFileList() {
        return fileList;
    }

    public void setFileList(String fileList) {
        this.fileList = fileList;
    }

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public Integer getM_publish_personid() {
        return m_publish_personid;
    }

    public void setM_publish_personid(Integer m_publish_personid) {
        this.m_publish_personid = m_publish_personid;
    }

    public Integer getM_typeid() {
        return m_typeid;
    }

    public void setM_typeid(Integer m_typeid) {
        this.m_typeid = m_typeid;
    }

    public String getM_title() {
        return m_title;
    }

    public void setM_title(String m_title) {
        this.m_title = m_title;
    }

    public String getM_datatime() {
        return m_datatime;
    }

    public void setM_datatime(String m_datatime) {
        this.m_datatime = m_datatime;
    }

    public Integer getM_readnum() {
        return m_readnum;
    }

    public void setM_readnum(Integer m_readnum) {
        this.m_readnum = m_readnum;
    }

    public String getM_content() {
        return m_content;
    }

    public void setM_content(String m_content) {
        this.m_content = m_content;
    }
}