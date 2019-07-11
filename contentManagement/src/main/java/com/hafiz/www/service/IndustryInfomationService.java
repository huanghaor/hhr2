package com.hafiz.www.service;

import com.hafiz.www.po.IndustryInformation;
import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.until.JsonResult;

/**
 * 行业资讯接口
 */
public interface IndustryInfomationService {

    /**
     * 管理员查询行业资讯信息
     * @param publicPagingQuery
     * @return
     */
    JsonResult selectIndustryInformationDataByPage(PublicPagingQuery publicPagingQuery);

    /**
     * 查询行业资讯信息游客
     * @param publicPagingQuery
     * @return
     */
    JsonResult selectIndustryInformationDataByPage_customer(PublicPagingQuery publicPagingQuery);

    /**
     * 修改行业资讯信息
     * @param industryInformation
     * @return
     */
    JsonResult updateIndustryInfo(IndustryInformation industryInformation);

    /**
     * 删除行业资讯信息
     * @param industryInformation
     * @return
     */
    JsonResult deleteIndustryInfo(IndustryInformation industryInformation);

    /**
     * 根据id查询详细信息
     * @param i_id
     * @return
     */
    JsonResult selectIndustryInfoById(int i_id);



}
