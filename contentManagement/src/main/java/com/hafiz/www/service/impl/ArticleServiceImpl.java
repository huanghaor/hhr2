package com.hafiz.www.service.impl;

import com.hafiz.www.mapper.TechnologyModuleEnclosureMapper;
import com.hafiz.www.mapper.TechnologyModuleMapper;
import com.hafiz.www.mapper.TypeOfArticleMapper;
import com.hafiz.www.po.*;
import com.hafiz.www.service.ArticleService;
import com.hafiz.www.shiro.SessionUtils;
import com.hafiz.www.until.GetTheTimeStamp;
import com.hafiz.www.until.JsonResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private TypeOfArticleMapper typeOfArticleMapper;
    @Autowired
    private TechnologyModuleMapper technologyModuleMapper;
    @Autowired
    private TechnologyModuleEnclosureMapper technologyModuleEnclosureMapper;
    /**
     * 获取技术文章分类列表
     * @return  LIST列表
     */
    public List<TypeOfArticle> getTheTypeOfArtileList() {
        return typeOfArticleMapper.getTheTypeOfArtileList();
    }

    /**
     * 保存新增文章
     * 1：先将文本内容保存至TechnologyModule表中
     * 2：将上面保存数据时返回的数据ID和上传的附件文件一起保存至TechnologyModuleEnclosure表中
     * @param technologyModule  上传数据
     * @return 返回保存的当前id
     */
    @Transactional
    public int insertSelective(TechnologyModule technologyModule) {
        //判断当前用户是否登陆
        int data=0;
        if(SessionUtils.isLoggedIn()){
            try {
                technologyModule.setM_publish_personid(SessionUtils.getLoginUserId());//用户id
                technologyModule.setM_datatime(new GetTheTimeStamp().getCurrentTime());//当前时间
                String content=technologyModule.getM_content();
                String fileList="";


                if(content.contains("border=\"0\"")){//将上传的留言中如果含有表格则将其border改为1
                    content=content.replace("border=\"0\"","border=\"1\"");
                }
                technologyModule.setM_content(content);
                technologyModule.setM_resources_mode("1");//原创
                data =technologyModuleMapper.insertSelective(technologyModule);
                //获取返回 的 数据id
                data = technologyModule.getM_id();
                List<TechnologyModuleEnclosure> lists = new ArrayList <TechnologyModuleEnclosure>();
                if(technologyModule.getFileList()!=null && !technologyModule.getFileList().equals("")){
                    fileList = technologyModule.getFileList();//上传文件保存路径
                    JSONObject jsonObject =   new JSONObject(fileList);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject_s =   new JSONObject();
                        jsonObject_s = (JSONObject)jsonArray.get(i);
                        TechnologyModuleEnclosure technologyModuleEnclosure = new TechnologyModuleEnclosure();
                        technologyModuleEnclosure.setE_enclosure_url(jsonObject_s.getString("url"));
                        technologyModuleEnclosure.setM_id(data);
                        technologyModuleEnclosure.setE_dowloads("0");
                        technologyModuleEnclosure.setE_filename(jsonObject_s.getString("e_filename"));
                        technologyModuleEnclosure.setE_filetype(jsonObject_s.getString("e_filetype"));
                        technologyModuleEnclosure.setE_filesize(jsonObject_s.getString("e_filesize"));
                        lists.add(technologyModuleEnclosure);
                    }
                   technologyModuleEnclosureMapper.insert(lists);
                }
            }catch (Exception e){
                e.printStackTrace();
                data=-2;
            }

        }else{
            data=-1;
        }

        return data;
    }

    /**
     * 技术文章条件查询
     * @param publicPagingQuery
     * @return
     */
    public JsonResult selectTechnoDataByConditon(PublicPagingQuery publicPagingQuery) {
        JsonResult jsonObject = new JsonResult();
        TechnologyModule technologyModule = new TechnologyModule();
        if(publicPagingQuery.getM_id() !=null && !publicPagingQuery.getM_id().equals("")){
            technologyModule.setM_id(Integer.parseInt(publicPagingQuery.getM_id()));
        }
        if(publicPagingQuery.getA_id() !=null && !publicPagingQuery.getA_id().equals("")){
            technologyModule.setM_typeid(Integer.parseInt(publicPagingQuery.getA_id()));
        }
        if(publicPagingQuery.getFYType().equals("owner")){
            if(SessionUtils.isLoggedIn()){
                int id=new SessionUtils().getLoginUserId();
                technologyModule.setM_publish_personid(id);//用于登陆用户根据用户id查询;
            }else{
                jsonObject=jsonObject.newInstanceFail("请先登录系统，再查询！");
                return jsonObject;
            }
        }
        try{
            publicPagingQuery.setQueryObj(technologyModule);
            List<TechnologyModule> list = technologyModuleMapper.selectTechnologyModuleAllData(publicPagingQuery);
            for(int i=0;i<list.size();i++){
                int t_id = list.get(i).getM_id();
                List<TechnologyModuleEnclosure> listUrl = technologyModuleEnclosureMapper.selectByPrimaryKey(t_id);
                list.get(i).setTechnology_module_enclosure(listUrl);
            }
            publicPagingQuery.setRows(list);
            publicPagingQuery.setTotal(technologyModuleMapper.getCount(publicPagingQuery));
            jsonObject= jsonObject.newInstanceSuccess(publicPagingQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 保存值技术文章
     * @param list
     * @return
     */
    public int SaveTheArticle(List<TechnologyModule> list){
        int isSuccess = technologyModuleMapper.insertSelectiveList(list);
        return isSuccess;
    }

    /**
     * 管理员文章条件查询
     * @param technologyModule
     * @return
     */
    public JsonResult selectArticleDataByManager(TechnologyModule technologyModule) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                List <TechnologyModule> list = technologyModuleMapper.selectArticleDataByManager(technologyModule);
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
     * 跟新数据设置为精帖
     * @param technologyModule
     * @return
     */
    public JsonResult updateArticleDataByManager(TechnologyModule technologyModule) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                int data = technologyModuleMapper.updateByPrimaryKeySelective(technologyModule);
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
            jsonResult=jsonResult.newInstanceFail("查询失败");
        }

        return jsonResult;
    }

    /**
     * 管理员操作技术文章批量删除
     * @param technologyModule
     * @return
     */
    public JsonResult deleteArticleDataByManager(TechnologyModule technologyModule) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                int data = technologyModuleMapper.deleteArticleDataByManager(technologyModule);
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
}
