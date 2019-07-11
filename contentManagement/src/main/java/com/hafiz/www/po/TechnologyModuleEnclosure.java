package com.hafiz.www.po;

/**
 *技术文章上传文件存储
 */
public class TechnologyModuleEnclosure {
    private Integer e_id;//文件id

    private Integer m_id;//文章id

    private String e_enclosure_url;//文章保存路径

    private String e_dowloads;//附件下载量

    private String e_filename;//文章重命名前的名称

    private String e_filetype;//文件类型

    private String e_filesize;//文件大小

    public String getE_filetype() {
        return e_filetype;
    }

    public void setE_filetype(String e_filetype) {
        this.e_filetype = e_filetype;
    }

    public String getE_filesize() {
        return e_filesize;
    }

    public void setE_filesize(String e_filesize) {
        this.e_filesize = e_filesize;
    }

    public Integer getE_id() {
        return e_id;
    }

    public void setE_id(Integer e_id) {
        this.e_id = e_id;
    }

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public String getE_enclosure_url() {
        return e_enclosure_url;
    }

    public void setE_enclosure_url(String e_enclosure_url) {
        this.e_enclosure_url = e_enclosure_url;
    }

    public String getE_dowloads() {
        return e_dowloads;
    }

    public void setE_dowloads(String e_dowloads) {
        this.e_dowloads = e_dowloads;
    }

    public String getE_filename() {
        return e_filename;
    }

    public void setE_filename(String e_filename) {
        this.e_filename = e_filename;
    }
}