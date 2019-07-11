package com.hafiz.www.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import com.hafiz.www.mapper.EssayDiaryMapper;
import com.hafiz.www.mapper.EssayDiaryTypeMapper;
import com.hafiz.www.po.EssayDiary;
import com.hafiz.www.po.EssayDiaryType;
import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.service.EssayDiaryService;
import com.hafiz.www.shiro.SessionUtils;
import com.hafiz.www.until.GetTheTimeStamp;
import com.hafiz.www.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EssayDiaryImpl implements EssayDiaryService{
    @Autowired
    private EssayDiaryMapper essayDiaryMapper;
    @Autowired
    private EssayDiaryTypeMapper essayDiaryTypeMapper;

    /**
     * 初始化加载日记数据
     * @param publicPagingQuery
     * @return
     */
    public JsonResult initEssayDiaryData(PublicPagingQuery publicPagingQuery){
        JsonResult jsonObject = new JsonResult();
        EssayDiary essayDiary = new EssayDiary();
        essayDiary.setE_status(publicPagingQuery.getW_status());
        if(publicPagingQuery.getE_id() !=null && !publicPagingQuery.getE_id().equals("")){
            essayDiary.setE_id(Integer.parseInt(publicPagingQuery.getE_id()));
        }
        if(publicPagingQuery.getFYType().equals("owner")){
            if(SessionUtils.isLoggedIn()){
                int id=new SessionUtils().getLoginUserId();
                essayDiary.setE_uid(id);//用于登陆用户根据用户id查询;
            }else{
                jsonObject=jsonObject.newInstanceFail("请先登录系统，再查询！");
                return jsonObject;
            }
        }
        try{
            publicPagingQuery.setQueryObj(essayDiary);
            publicPagingQuery.setRows(essayDiaryMapper.selectEssayDiaryData(publicPagingQuery));
            publicPagingQuery.setTotal(essayDiaryMapper.getCount(publicPagingQuery));
            jsonObject= jsonObject.newInstanceSuccess(publicPagingQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     *加载全部日记类别数据
      * @return 数据list
     */
    public JsonResult initTheDiaryTypeList() {
        JsonResult jsonResult = new JsonResult();
        try {
            List<EssayDiaryType> list =essayDiaryTypeMapper.selectAllData();
            if(list.size()>0){
                jsonResult = jsonResult.newInstanceSucess_d2(list);
            }else{
                jsonResult = jsonResult.newInstanceFail("暂无数据！");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = jsonResult.newInstanceFail("数据加载失败，请稍后再试！");
        }
        return jsonResult;
    }

    /**
     * 添加日记类别数据
     * @param essayDiaryType 添加数据
     * @return 数据添加状态
     */
    public JsonResult addTheDiaryType(EssayDiaryType essayDiaryType) {
        JsonResult jsonResult = new JsonResult();
        try {
            if(SessionUtils.isLoggedIn()){
                essayDiaryType.setE_add_time(new GetTheTimeStamp().getCurrentTime());
                essayDiaryType.setE_add_usrid(SessionUtils.getLoginUserId());
                essayDiaryType.setE_type_status("1");
                int data = essayDiaryTypeMapper.insertSelective(essayDiaryType);
                if(data>0){
                    jsonResult = jsonResult.newInstanceSucess_d1("添加成功！");
                }else{
                    jsonResult = jsonResult.newInstanceFail("操作失败，请稍后再试！");
                }
            }else{
                jsonResult = jsonResult.newInstanceFail("请登陆系统！");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = jsonResult.newInstanceFail("操作失败，请稍后再试！");
        }
        return jsonResult;
    }

    /**
     * 添加日记
     * @param essayDiary 日记数据
     * @return 添加状态
     */
    public JsonResult addEssayDiaryData(EssayDiary essayDiary) {

        JsonResult jsonResult = new JsonResult();
        try {
            if(SessionUtils.isLoggedIn()){
                essayDiary.setE_addtime(new GetTheTimeStamp().getCurrentTime());
                essayDiary.setE_readnumber(0);
                essayDiary.setE_status("1");
                essayDiary.setE_uid(SessionUtils.getLoginUserId());
                if(essayDiary.getE_url()!=null && !essayDiary.getE_url().equals("")){
                    String fileList = essayDiary.getE_url();//上传文件保存路径
                    JSONObject jsonObject =   new JSONObject(fileList);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject_s =   new JSONObject();
                        jsonObject_s = (JSONObject)jsonArray.get(i);
                        essayDiary.setE_url(jsonObject_s.getString("url"));
                    }
                }
                int data = essayDiaryMapper.insertSelective(essayDiary);
                if(data>0){
                    jsonResult = jsonResult.newInstanceSucess_d1("添加成功！");
                }else{
                    jsonResult = jsonResult.newInstanceFail("操作失败，请稍后再试！");
                }
            }else{
                jsonResult = jsonResult.newInstanceFail("请登陆系统！");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult = jsonResult.newInstanceFail("操作失败，请稍后再试！");
        }
        return jsonResult;
    }

    /**
     * 添加阅读量
     */
    public void addTheReadNum(String e_id){
        try {
            EssayDiary essayDiary=essayDiaryMapper.selectByPrimaryKey(Integer.parseInt(e_id));
            int readnum = essayDiary.getE_readnumber();
            if(readnum==0){
                readnum=1;
            }else{
                readnum=readnum+1;
            }
            essayDiary.setE_readnumber(readnum);
            essayDiaryMapper.updateByPrimaryKeySelective(essayDiary);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("阅读量数据添加失败");
        }

    }
    /**
     * 管理员日记条件查询
     * @param essayDiary
     * @return
     */
    public JsonResult selectEssayDiaryDataByManager(EssayDiary essayDiary) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                List <EssayDiary> list = essayDiaryMapper.selectEssayDiaryDataByManager(essayDiary);
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
     * 跟新日记数据设置为精帖
     * @param essayDiary
     * @return
     */
    public JsonResult updateEssayDiaryDataByManager(EssayDiary essayDiary) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                int data = essayDiaryMapper.updateByPrimaryKeySelective(essayDiary);
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
     * 管理员操作日记批量删除
     * @param essayDiary
     * @return
     */
    public JsonResult deleteEssayDiaryDataByManager(EssayDiary essayDiary) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                int data = essayDiaryMapper.deleteEssayDiaryDataByManager(essayDiary);
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
