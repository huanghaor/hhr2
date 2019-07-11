package com.hafiz.www.service.impl;

import com.alibaba.fastjson.JSON;
import com.hafiz.www.mapper.TravelCollectionMapper;
import com.hafiz.www.mapper.TravelPictureUrlMapper;
import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.po.TravelCollection;
import com.hafiz.www.po.TravelPictureUrl;
import com.hafiz.www.service.DonkeyTripService;
import com.hafiz.www.shiro.SessionUtils;
import com.hafiz.www.until.GetTheTimeStamp;
import com.hafiz.www.until.JsonResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 驴行游记接口实现方法
 */
@Service
public class DonkeyTripImpl implements DonkeyTripService{

    @Autowired
    private TravelCollectionMapper travelCollectionMapper;
    @Autowired
    private TravelPictureUrlMapper travelPictureUrlMapper;

    /**
     * 保存发布数据
     * @param travelCollection  上传数据
     * @return
     */
    @Transactional
    public int insertSelective(TravelCollection travelCollection) {
        //判断当前用户是否登陆
        int data=0;
        if(SessionUtils.isLoggedIn()){
            try {
                travelCollection.setT_create_time(new GetTheTimeStamp().getCurrentTime());
                travelCollection.setU_id(SessionUtils.getLoginUserId());
                String content=travelCollection.getT_tourism_strategy();
                String datebegin = travelCollection.getT_begin_time();
                String dateend = travelCollection.getT_end_time();
                datebegin = datebegin.replace("GMT", "").replaceAll("\\(.*\\)", "");
                dateend = dateend.replace("GMT", "").replaceAll("\\(.*\\)", "");
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z",Locale.ENGLISH);
                try{
                    Date date_begin = sdf.parse(datebegin);
                    Date date_end = sdf.parse(dateend);
                    travelCollection.setT_begin_time(new SimpleDateFormat("yyyy-MM-dd").format(date_begin));
                    travelCollection.setT_end_time(new SimpleDateFormat("yyyy-MM-dd").format(date_end));
                }catch(Exception e){
                    e.printStackTrace();
                }
                String fileList="";
                if(content.contains("border=\"0\"")){//将上传的留言中如果含有表格则将其border改为1
                    content=content.replace("border=\"0\"","border=\"1\"");
                }
                travelCollection.setT_tourism_strategy(content);
                travelCollection.setT_fine_paste("0");//默认设置为非精贴
                data =travelCollectionMapper.insertSelective(travelCollection);
                //获取返回 的 数据id
                data = travelCollection.getT_id();
                List<TravelPictureUrl> lists = new ArrayList<TravelPictureUrl>();
                if(travelCollection.getFileList()!=null && !travelCollection.getFileList().equals("")){
                    fileList = travelCollection.getFileList();//上传文件保存路径
                    JSONObject jsonObject =   new JSONObject(fileList);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject_s =   new JSONObject();
                        jsonObject_s = (JSONObject)jsonArray.get(i);
                        TravelPictureUrl travelPictureUrl = new TravelPictureUrl();
                        travelPictureUrl.setT_id(data);
                        travelPictureUrl.setP_picture_url(jsonObject_s.getString("url"));
                        travelPictureUrl.setP_picture_size(jsonObject_s.getString("e_filesize"));
                        travelPictureUrl.setP_upload_personid(SessionUtils.getLoginUserId());
                        travelPictureUrl.setP_upload_time(new GetTheTimeStamp().getCurrentTime());
                        lists.add(travelPictureUrl);
                    }
                    travelPictureUrlMapper.insert(lists);
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
     * 查询驴行集锦数据数据
     * @param publicPagingQuery 查询条件
     * @return 对象集合
     */
    public void selectMessageData(PublicPagingQuery publicPagingQuery) {
        //根据条件查询出驴行集锦数据
        List<TravelCollection> list = travelCollectionMapper.selectMessageData(publicPagingQuery);
        //根据查询出来的数据id循环查出对应的图片存储路径
        for(int i=0;i<list.size();i++){
            int t_id = list.get(i).getT_id();
            List<TravelPictureUrl> listUrl = this.selectPictureUrlListByPrimaryKey(t_id);
            list.get(i).setTravel_picture_url(listUrl);
        }
        publicPagingQuery.setRows(list);
        publicPagingQuery.setTotal(travelCollectionMapper.getCount(publicPagingQuery));
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
     * 查询对象根据数据id
     * @param t_id 数据id
     * @return 实体对象
     */
    public TravelCollection selectByPrimaryKey(Integer t_id) {
        return travelCollectionMapper.selectByPrimaryKey(t_id);
    }

    /**
     * 跟新数据
     * @param record 更新记录数据
     * @return 跟新状态：影响行数
     */
    public int updateByPrimaryKeySelective(TravelCollection record) {
        return travelCollectionMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 管理员条件查询
     * @param travelCollection
     * @return
     */
    public JsonResult selectMessageDataByManager(TravelCollection travelCollection) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                List <TravelCollection> list = travelCollectionMapper.selectMessageDataByManager(travelCollection);
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
     * 删除游记
     * @param t_id
     * @return
     */
    public JsonResult deleteByPrimaryKey(int t_id) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                String result="";
               int data = travelCollectionMapper.deleteByPrimaryKey(t_id);
               if(data>0){
                   result="操作成功";
               }else{
                   result="操作失败";
               }
                jsonResult = jsonResult.newInstanceSucess_d1(result);
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
     * 管理员跟新数据
     * @param travelCollection
     * @return
     */
    public JsonResult updateDonkeyTripById(TravelCollection travelCollection) {
        JsonResult jsonResult = new JsonResult();
        try{
            if(SessionUtils.isLoggedIn()) {
                String result="";
                int data = travelCollectionMapper.updateByPrimaryKeySelective(travelCollection);
                if(data>0){
                    result="操作成功";
                }else{
                    result="操作失败";
                }
                jsonResult = jsonResult.newInstanceSucess_d1(result);
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
