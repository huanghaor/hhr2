package com.hafiz.www.service.impl;

import com.hafiz.www.mapper.*;
import com.hafiz.www.po.*;
import com.hafiz.www.service.IndexService;
import com.hafiz.www.shiro.SessionUtils;
import com.hafiz.www.until.GetTheTimeStamp;
import com.hafiz.www.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private NavigationBarRecordMapper navigationBarRecordMapper;
    @Autowired
    private EssayDiaryMapper essayDiaryMapper;
    @Autowired
    private TechnologyModuleMapper technologyModuleMapper;
    @Autowired
    private MessageWalMapper messageWalMapper;
    @Autowired
    private TravelCollectionMapper travelCollectionMapper;
    @Autowired
    private TravelPictureUrlMapper travelPictureUrlMapper;
    @Autowired
    private IndustryInformationMapper industryInformationMapper;

    /**
     * 获取导航栏列表
     * @return
     */
    public List<NavigationBarRecord> getIndexNavList() {
        NavigationBarRecord navigationBarRecord = new NavigationBarRecord();
        if(SessionUtils.isLoggedIn()){
            if(SessionUtils.getUserJurisdiction().equals("customer")){
                navigationBarRecord.setN_jurisdiction("customer");
            }
        }else{
            navigationBarRecord.setN_jurisdiction("customer");
        }
        return navigationBarRecordMapper.getIndexNavLists(navigationBarRecord);
    }

    /**
     * 管理员获取主页导航
     * @return
     */
    public JsonResult getIndexNavListsByManager() {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                List <NavigationBarRecord> list = navigationBarRecordMapper.getIndexNavListsByManager();
                jsonResult = jsonResult.newInstanceSucess_d2(list);
            }else{
                jsonResult=jsonResult.newInstanceFail("请登陆系统再进行查询");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=jsonResult.newInstanceFail("查询失败");
        }

        return jsonResult;
    }

    /**
     * 初始化首页页面数据
     * @return
     */
    public JsonResult initIndexData(HttpServletRequest req){
        JsonResult jsonResult = new JsonResult();
        InitIndexDataEntity initIndexDataEntity = new InitIndexDataEntity();

        try {
            //在获取首页初始化数据时，获取系统当前访问者的电脑IP和用户id
           /* ULogonrecord uLogonrecord = new ULogonrecord();
            if(SessionUtils.isLoggedIn()){
                String remoteAddr = req.getRemoteAddr();
                uLogonrecord.setU_computer_ip(remoteAddr);
                uLogonrecord.setU_logintime();
                System.out.println("=====当前系统用户IP====:"+remoteAddr+"====用户账号："+SessionUtils.getLoginUserId());
            }else{
                String remoteAddr = req.getRemoteAddr();
                System.out.println("=====当前系统用户IP====:"+remoteAddr+"====用户账号：000");
            }
*/

            //加载日记：阅读量最高的三篇
            List<EssayDiary>  listEssayDiary = essayDiaryMapper.selectEssayDiaryDataTopThree();
            initIndexDataEntity.setEssayDiaries(listEssayDiary);
            //查询技术文章最新10条数据
            List<TechnologyModule> listTechnologyModule = technologyModuleMapper.selectTechnologyModuleDataTopNewPublish();
            initIndexDataEntity.setTechnologyModules(listTechnologyModule);
            //查询行业资讯推荐前10条数据
            List<IndustryInformation> industryInformationsList = industryInformationMapper.selectIndustryInformationTopTenByFine_paste();
            initIndexDataEntity.setIndustryInformations(industryInformationsList);
            //查询社区问答最新10条数据
            List<MessageWal> listMessageWal = messageWalMapper.selecttMessageDataTopNewPublish();
            initIndexDataEntity.setMessageWals(listMessageWal);

            //驴行游记精贴
            PublicPagingQuery publicPagingQuery = new PublicPagingQuery();
            publicPagingQuery.setPageSize(4);
            publicPagingQuery.setPageNumber(1);
            TravelCollection travelCollection = new TravelCollection();
            travelCollection.setT_fine_paste("1");
            publicPagingQuery.setQueryObj(travelCollection);
            List<TravelCollection> listTravelCollection = travelCollectionMapper.selectMessageData(publicPagingQuery);
            //根据查询出来的数据id循环查出对应的图片存储路径
            for(int i=0;i<listTravelCollection.size();i++){
                int t_id = listTravelCollection.get(i).getT_id();
                List<TravelPictureUrl> listUrl = this.selectPictureUrlListByPrimaryKey(t_id);
                listTravelCollection.get(i).setTravel_picture_url(listUrl);
            }
            initIndexDataEntity.setTravelCollections(listTravelCollection);
            jsonResult =jsonResult.newInstanceSucess_d2(initIndexDataEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonResult;
    }
    /**
     * 根据驴行数据id查询其对应图片保存路径
     * @param t_id  驴行数据id
     * @return 回复对话集合
     */
    public List<TravelPictureUrl> selectPictureUrlListByPrimaryKey(Integer t_id){
        List<TravelPictureUrl> list =new ArrayList <TravelPictureUrl>();
        try {
            list=travelPictureUrlMapper.selectTravleIDDataByPrimaryKey(t_id);
        }catch (Exception e){
            e.printStackTrace();
            list=null;
        }
        return list;
    }

    /**
     * 导航栏修改数据
     * @param navigationBarRecord
     * @return
     */
    public JsonResult updateNavigation(NavigationBarRecord navigationBarRecord) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                int data= navigationBarRecordMapper.updateByPrimaryKeySelective(navigationBarRecord);
                if(data>0){
                    jsonResult = jsonResult.newInstanceSucess_d1("操作成功");
                }else{
                    jsonResult = jsonResult.newInstanceSucess_d1("操作失败");
                }

            }else{
                jsonResult=jsonResult.newInstanceFail("请登陆系统再进行查询");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=jsonResult.newInstanceFail("操作失败");
        }

        return jsonResult;
    }

    /**
     * 新增导航栏
     * @param navigationBarRecord
     * @return
     */
    public JsonResult insertNavigation(NavigationBarRecord navigationBarRecord) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                navigationBarRecord.setN_status("1");
                navigationBarRecord.setN_person(String.valueOf(SessionUtils.getLoginUserId()));
                navigationBarRecord.setN_time(new GetTheTimeStamp().getCurrentTime());
                int data= navigationBarRecordMapper.insertSelective(navigationBarRecord);
                if(data>0){
                    jsonResult = jsonResult.newInstanceSucess_d1("保存成功");
                }else{
                    jsonResult = jsonResult.newInstanceSucess_d1("操作失败");
                }

            }else{
                jsonResult=jsonResult.newInstanceFail("请登陆系统再进行查询");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=jsonResult.newInstanceFail("保存失败");
        }

        return jsonResult;
    }
}
