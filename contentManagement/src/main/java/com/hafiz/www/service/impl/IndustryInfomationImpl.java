package com.hafiz.www.service.impl;

import com.hafiz.www.mapper.IndustryInformationMapper;
import com.hafiz.www.po.IndustryInformation;
import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.service.IndustryInfomationService;
import com.hafiz.www.shiro.SessionUtils;
import com.hafiz.www.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IndustryInfomationImpl implements IndustryInfomationService {
    @Autowired
    private IndustryInformationMapper industryInformationMapper;
    /**
     * 行业资讯信息查询
     * @param publicPagingQuery
     * @return
     */
    public JsonResult selectIndustryInformationDataByPage(PublicPagingQuery publicPagingQuery) {
        JsonResult jsonObject = new JsonResult();
        IndustryInformation industryInformation = new IndustryInformation();
        try{
            if(publicPagingQuery.getI_fine_paste()!=null){
                industryInformation.setI_fine_paste(publicPagingQuery.getI_fine_paste());
            }
            if(publicPagingQuery.getI_resources_mode()!=null){
                industryInformation.setI_resources_mode(publicPagingQuery.getI_resources_mode());
            }
            if(publicPagingQuery.getI_title()!=null){
                industryInformation.setI_title(publicPagingQuery.getI_title());
            }
            publicPagingQuery.setQueryObj(industryInformation);
            List<IndustryInformation> list = industryInformationMapper.selectIndustryInformationDataByPage(publicPagingQuery);
            publicPagingQuery.setRows(list);
            publicPagingQuery.setTotal(industryInformationMapper.getCount(publicPagingQuery));
            jsonObject= jsonObject.newInstanceSuccess(publicPagingQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }


    /**
     * 管理员操作修改行业资讯信息
     * @param industryInformation
     * @return
     */
    public JsonResult updateIndustryInfo(IndustryInformation industryInformation) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                int data = industryInformationMapper.updateByPrimaryKeySelective(industryInformation);
                if(data>0){
                    jsonResult = jsonResult.newInstanceSucess_d1("操作成功");
                }else{
                    jsonResult=jsonResult.newInstanceFail("操作失败");
                }

            }else{
                jsonResult=jsonResult.newInstanceFail("请登陆系统再进行操作");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=jsonResult.newInstanceFail("操作失败");
        }
        return jsonResult;
    }
    /**
     * 管理员操作删除行业资讯信息
     * @param industryInformation
     * @return
     */
    public JsonResult deleteIndustryInfo(IndustryInformation industryInformation) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                int data = industryInformationMapper.deleteByManager(industryInformation);
                if(data>0){
                    jsonResult = jsonResult.newInstanceSucess_d1("操作成功");
                }else{
                    jsonResult=jsonResult.newInstanceFail("操作失败");
                }

            }else{
                jsonResult=jsonResult.newInstanceFail("请登陆系统再进行操作");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=jsonResult.newInstanceFail("操作失败");
        }
        return jsonResult;
    }
    /**
     * 根据id查询详细信息
     * @param i_id
     * @return
     */
    public JsonResult selectIndustryInfoById(int i_id) {
        JsonResult jsonResult = new JsonResult();
        try{
            IndustryInformation industryInformation = industryInformationMapper.selectByPrimaryKey(i_id);
            jsonResult = jsonResult.newInstanceSucess_d2(industryInformation);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=jsonResult.newInstanceFail("操作失败");
        }
        return jsonResult;
    }

    /**
     * 查询行业资讯信息游客
     * @param publicPagingQuery
     * @return
     */
    public JsonResult selectIndustryInformationDataByPage_customer(PublicPagingQuery publicPagingQuery) {
        JsonResult jsonObject = new JsonResult();
        IndustryInformation industryInformation = new IndustryInformation();
        try{
            if(publicPagingQuery.getI_fine_paste()!=null){
                industryInformation.setI_fine_paste(publicPagingQuery.getI_fine_paste());
            }
            if(publicPagingQuery.getI_resources_mode()!=null){
                industryInformation.setI_resources_mode(publicPagingQuery.getI_resources_mode());
            }
            if(publicPagingQuery.getI_title()!=null){
                industryInformation.setI_title(publicPagingQuery.getI_title());
            }
            publicPagingQuery.setQueryObj(industryInformation);
            //主体数据
            List<IndustryInformation> list = industryInformationMapper.selectIndustryInformationDataByPage(publicPagingQuery);
            publicPagingQuery.setRows(list);
            //时间排序前10
            List<IndustryInformation> list_time = industryInformationMapper.selectIndustryInformationTopTenByTime();
            publicPagingQuery.setRows1(list_time);
            //推荐前10
            List<IndustryInformation> list_Fine_paste = industryInformationMapper.selectIndustryInformationTopTenByFine_paste();
            publicPagingQuery.setRows2(list_Fine_paste);
            publicPagingQuery.setTotal(industryInformationMapper.getCount(publicPagingQuery));
            jsonObject= jsonObject.newInstanceSuccess(publicPagingQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
}
