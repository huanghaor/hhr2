package com.hafiz.www.service.impl;

import com.hafiz.www.mapper.TravelCommentMapper;
import com.hafiz.www.mapper.TravelCommentReplyMapper;
import com.hafiz.www.po.TravelComment;
import com.hafiz.www.po.TravelCommentReply;
import com.hafiz.www.service.DonkeyTripCommentService;
import com.hafiz.www.shiro.SessionUtils;
import com.hafiz.www.until.GetTheTimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 游记评论接口实现类
 */
@Service
public class DonkeyTripCommentImpl implements DonkeyTripCommentService{

    @Autowired
    private TravelCommentMapper travelCommentMapper;
    @Autowired
    private TravelCommentReplyMapper travelCommentReplyMapper;
    /**
     * 保存评论数据
     * @param record 保存数据
     * @return
     */
    public int insertSelective(TravelComment record) {
        int data=0;
        try {
            if(SessionUtils.isLoggedIn()){//判断当前用户是否登陆
                GetTheTimeStamp getTheTimeStamp = new GetTheTimeStamp();
                record.setC_commenttator_id(SessionUtils.getLoginUserId());
                record.setC_statu("1");
                record.setC_comment_time((getTheTimeStamp.getCurrentTime()).toString());
                record.setC_praisenumber(0);
                data =travelCommentMapper.insertSelective(record);
            }else{
                data=-2;
            }
        }catch (Exception e){
            e.printStackTrace();
            data=0;
        }
        return data;
    }

    /**
     * 初始化评论及回复数据
     * @param tc_id 游记数据id
     * @return list集合
     */
    public List<TravelComment> selectCommentAndReplyData(Integer tc_id) {
        List<TravelComment> list =new ArrayList<TravelComment>();
        try {
            list=travelCommentMapper.selectCommentAndReplyData(tc_id);
            if(list.size()!=0){
                for(int i=0;i<list.size();i++){
                    List<TravelCommentReply> list_reply=this.getTheTravelReply(list.get(i).getC_id());
                    if(list_reply.size()!=0){
                        list.get(i).setTravel_comment_reply(list_reply);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            list=null;
        }
        return list;
    }

    /**
     * 根据评论数据ID查询对应的所有回复数据
     * @param c_id 评论id
     * @return
     */
    public List<TravelCommentReply> getTheTravelReply(Integer c_id){
        List<TravelCommentReply> list =travelCommentReplyMapper.getTheTravelCommentReplyByC_id(c_id);
        return list;
    }

    /**
     * 保存游记评论回复对话数据
     * @param travelCommentReply 保存数据
     * @return 保存状态
     */
    public int saveTheTravelReply(TravelCommentReply travelCommentReply){
        //先根据对话发起人id和对话接收人id,相关留言id进行查询判断当前是否有值，得出结果进行排序

        int status=0;
        if(SessionUtils.getLoginUserId()==0){
            status=-1;
        }else{
            try{
                travelCommentReply.setReply_from_uid(SessionUtils.getLoginUserId());
                List<TravelCommentReply> listdata = this.selectReplyData(travelCommentReply);
                travelCommentReply.setReply_statu("1");
                travelCommentReply.setReply_add_time(new GetTheTimeStamp().getCurrentTime());
                if(listdata.size()==0){//则进行新增
                    //int reply_sameid_with_two_person = this.selectMaxWithReply_sameid_with_two_person();
                    travelCommentReply.setReply_sameid_with_two_person("1");
                    travelCommentReply.setReply_addid_with_two_person("1");
                }else{//在已有对话中进行追加
                    TravelCommentReply record_1 = new TravelCommentReply();
                    record_1.setReply_from_uid(SessionUtils.getLoginUserId());
                    record_1.setReply_to_uid(travelCommentReply.getReply_to_uid());
                    record_1.setReplyc_id(travelCommentReply.getReplyc_id());
                    int reply_addid_with_two_person = this.selectMaxWithreply_addid_with_two_person(record_1);
                    travelCommentReply.setReply_addid_with_two_person(String.valueOf(reply_addid_with_two_person+1));
                    travelCommentReply.setReply_sameid_with_two_person(listdata.get(0).getReply_sameid_with_two_person());
                }
                status= travelCommentReplyMapper.insertSelective(travelCommentReply);
            }catch (Exception e){
                e.printStackTrace();
                status=0;
            }
        }
        return status;
    }
    /**
     * 查询表中Reply_sameid_with_two_person字段的最大值
     * @return 数值
     */
    public int selectMaxWithReply_sameid_with_two_person(){
        return travelCommentReplyMapper.selectMaxWithReply_sameid_with_two_person();
    }
    /**
     * 根据reply_from_uid，reply_to_uid，remark_id 查询其reply_addid_with_two_person字段的最大值
     * @param record 查询条件
     * @return 数值
     */
    int selectMaxWithreply_addid_with_two_person(TravelCommentReply record){
        return travelCommentReplyMapper.selectMaxWithreply_addid_with_two_person(record);
    }
    /**
     * 根据字段进行查询
     * @param record 相关字段条件
     * @return list集合
     */
    public List<TravelCommentReply> selectReplyData(TravelCommentReply record){
        return travelCommentReplyMapper.selectReplyData(record);

    }
}
