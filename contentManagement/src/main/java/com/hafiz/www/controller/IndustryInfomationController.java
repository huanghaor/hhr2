package com.hafiz.www.controller;

import com.hafiz.www.po.IndustryInformation;
import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.service.IndustryInfomationService;
import com.hafiz.www.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 行业资讯controller接口
 */
@Controller
@RequestMapping("industryInfomation")
public class IndustryInfomationController {

    @Autowired
    private IndustryInfomationService industryInfomationService;

    /**
     * 跳转新闻资讯详情页面
     * @param req 返回modelAndView对象
     * @return
     */
    @RequestMapping(value = "/jumpToTheindustryInfomationdetail",method = RequestMethod.GET)
    public ModelAndView jumpToTheTheindustryInfomationdetail(HttpServletRequest req){

        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("IndustryInformation/industryInfoDetail");
        modelAndView.addObject("i_id",req.getParameter("i_id"));
        return modelAndView;
    }

    /**
     * 行业资讯分页查询管理员
     * @param publicPagingQuery
     * @return
     */
    @RequestMapping(value = "selectIndustryInfoByPageList" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult selectIndustryInfoByPage(PublicPagingQuery publicPagingQuery){
        return industryInfomationService.selectIndustryInformationDataByPage(publicPagingQuery);
    }
    /**
     * 行业资讯分页查询游客
     * @param publicPagingQuery
     * @return
     */
    @RequestMapping(value = "selectIndustryInfoByPageListBycustomer" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult selectIndustryInfoByPageBycustomer(PublicPagingQuery publicPagingQuery){
        return industryInfomationService.selectIndustryInformationDataByPage_customer(publicPagingQuery);
    }
    /**
     * 行业资讯信息修改
     * @param industryInformation
     * @return
     */
    @RequestMapping(value = "updateIndustryInfoByManager" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateIndustryInfoByManager(IndustryInformation industryInformation){
        return industryInfomationService.updateIndustryInfo(industryInformation);
    }
    /**
     * 行业资讯删除
     * @param industryInformation
     * @return
     */
    @RequestMapping(value = "deleteIndustryInfoByManager" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteIndustryInfoByManager(IndustryInformation industryInformation){
        return industryInfomationService.deleteIndustryInfo(industryInformation);
    }

    /**
     * 行业资讯详情查询
     * @param industryInformation
     * @return
     */
    @RequestMapping(value = "selectIndustryInfoById" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonResult selectIndustryInfoById(IndustryInformation industryInformation){
        return industryInfomationService.selectIndustryInfoById(industryInformation.getI_id());
    }



}
