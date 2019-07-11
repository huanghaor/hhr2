package com.hafiz.www.po;

import java.util.List;

/**
 * 分页查询公共实体
 */
public class PublicPagingQuery<T> {

    private  Integer pageNumber;//当前页码
    private  Integer pageSize;//每页显示数量
    private Integer total;      //总记录行数
    private Integer startRow;   //查询起始行
    private String w_status;//
    private List<T> rows;       //查询结果数据
    private List<T> rows1;       //查询结果数据
    private List<T> rows2;       //查询结果数据
    private T queryObj;         //查询对象
    private String FYType;//分页类型查询   all：即查询全部；onwer:查询自己
    private String type;//查询条件：1：根据点赞数排列；2：根据时间排列 3：默认


    private String e_id;//该字段专门用于随笔日记中的查询

    private String m_id;//该字段专门用于技术文章单条新详细查询
    private String a_id;//该字段专门用于技术文章根据文章类型查询

    private String t_id;//该字段专门用于驴行游记中的查询
    private String t_fine_paste;//驴行游记精帖查询：

    private String i_id;//行业资讯信息查询
    private String i_fine_paste;//行业资讯条件查询--精帖
    private String i_resources_mode;//行业资讯条件查询--资源获取方法
    private String i_title;//行业资讯条件查询---标题查询

    public List <T> getRows1() {
        return rows1;
    }

    public void setRows1(List <T> rows1) {
        this.rows1 = rows1;
    }

    public List <T> getRows2() {
        return rows2;
    }

    public void setRows2(List <T> rows2) {
        this.rows2 = rows2;
    }

    public String getI_fine_paste() {
        return i_fine_paste;
    }

    public void setI_fine_paste(String i_fine_paste) {
        this.i_fine_paste = i_fine_paste;
    }

    public String getI_resources_mode() {
        return i_resources_mode;
    }

    public void setI_resources_mode(String i_resources_mode) {
        this.i_resources_mode = i_resources_mode;
    }

    public String getI_title() {
        return i_title;
    }

    public void setI_title(String i_title) {
        this.i_title = i_title;
    }

    public String getI_id() {
        return i_id;
    }

    public void setI_id(String i_id) {
        this.i_id = i_id;
    }

    public String getT_fine_paste() {
        return t_fine_paste;
    }

    public void setT_fine_paste(String t_fine_paste) {
        this.t_fine_paste = t_fine_paste;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public Integer getStartRow() {
        if(pageNumber!=null && pageSize!=null) {
            return (pageNumber - 1) * pageSize;
        } else {
            return 0;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getW_status() {
        return w_status;
    }

    public void setW_status(String w_status) {
        this.w_status = w_status;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void setQueryObj(T queryObj) {
        this.queryObj = queryObj;
    }

    public T getQueryObj() {
        return queryObj;
    }

    public String getFYType() {
        return FYType;
    }

    public void setFYType(String FYType) {
        this.FYType = FYType;
    }
}
