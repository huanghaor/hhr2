package com.hafiz.www.po;

import java.util.Date;

/**
 * 页面导航按钮表
 */
public class NavigationBarRecord {
    private Integer n_id;//数据id

    private String n_name;//菜单名称

    private String n_menu_gradation;//菜单级次	列如：1级  2级  3级

    private String n_parent_menu;//父菜单级次	例如：该菜单为菜单级次1级下的子菜单，则该字段记录为001；如菜单级次为2级下的，则该字段记录为002

    private String n_menu_type;//菜单类别

    private String n_url;//系统路径

    private String n_time;//添加时间

    private String n_person;//添加人

    private String n_status;//导航栏状态设置：1：启用；0禁用

    private String n_jurisdiction;//权限：admin;customer

    public String getN_jurisdiction() {
        return n_jurisdiction;
    }

    public void setN_jurisdiction(String n_jurisdiction) {
        this.n_jurisdiction = n_jurisdiction;
    }

    public String getN_status() {
        return n_status;
    }

    public void setN_status(String n_status) {
        this.n_status = n_status;
    }

    public Integer getN_id() {
        return n_id;
    }

    public void setN_id(Integer n_id) {
        this.n_id = n_id;
    }

    public String getN_name() {
        return n_name;
    }

    public void setN_name(String n_name) {
        this.n_name = n_name;
    }

    public String getN_menu_gradation() {
        return n_menu_gradation;
    }

    public void setN_menu_gradation(String n_menu_gradation) {
        this.n_menu_gradation = n_menu_gradation;
    }

    public String getN_parent_menu() {
        return n_parent_menu;
    }

    public void setN_parent_menu(String n_parent_menu) {
        this.n_parent_menu = n_parent_menu;
    }

    public String getN_menu_type() {
        return n_menu_type;
    }

    public void setN_menu_type(String n_menu_type) {
        this.n_menu_type = n_menu_type;
    }

    public String getN_url() {
        return n_url;
    }

    public void setN_url(String n_url) {
        this.n_url = n_url;
    }

    public String getN_time() {
        return n_time;
    }

    public void setN_time(String n_time) {
        this.n_time = n_time;
    }

    public String getN_person() {
        return n_person;
    }

    public void setN_person(String n_person) {
        this.n_person = n_person;
    }
}