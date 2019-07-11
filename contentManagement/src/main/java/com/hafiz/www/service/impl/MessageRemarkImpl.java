package com.hafiz.www.service.impl;

import com.hafiz.www.mapper.MessageRemarkMapper;
import com.hafiz.www.mapper.MessageRemarkReplyMapper;
import com.hafiz.www.po.MessageRemark;
import com.hafiz.www.po.MessageRemarkReply;
import com.hafiz.www.service.MessageRemarkService;
import com.hafiz.www.shiro.SessionUtils;
import com.hafiz.www.until.GetTheTimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageRemarkImpl implements MessageRemarkService {

    @Autowired
    private MessageRemarkMapper messageRemarkMapper;
    @Autowired
    private MessageRemarkReplyMapper messageRemarkReplyMapper;
    /**
     * 统计评论数量
     * @param remark_message_id 留言墙数据id
     * @return 评论数
     */
    public int getCount(String remark_message_id) {
        int data=0;
        try {
            data= messageRemarkMapper.getCount(remark_message_id);
        }catch (Exception e){
            e.printStackTrace();
            data=0;
        }
        return data;
    }

    /**
     * 评论保存
     * @param record  评论信息
     * @return 评论状态
     */
    public int insertSelective(MessageRemark record) {
        int data=0;
        try {
            //获取当前系统登陆用户
            record.setRemark_statu("1");//未删除
            if(SessionUtils.getLoginUserId()==0){
                data=-1;
            }else{
                record.setRemark_from_uid(SessionUtils.getLoginUserId());//用户id
                record.setRemark_add_time(new GetTheTimeStamp().getCurrentTime());//当前时间
                data= messageRemarkMapper.insertSelective(record);
            }

        }catch (Exception e){
            e.printStackTrace();
            data=0;
        }
        return data;
    }

    /**
     * 根据留言墙id 查询其对应所有的评论
     * @param remark_message_id 留言墙数据id
     * @return 评论集合
     */
    public List<MessageRemark> selectRemarkAndReplyData(Integer remark_message_id) {
        List<MessageRemark> list =new ArrayList <MessageRemark>();
        try {
            //查询评论
            list=messageRemarkMapper.selectRemarkAndReplyData(remark_message_id);
            for(int i=0;i<list.size();i++){
                int remark_reply_id = list.get(i).getRemark_id();
                //查询评论下的所有回复对话
                List<MessageRemarkReply> listReply = this.selectReplyDataByPrimaryKey(remark_reply_id);
                list.get(i).setMessageRemarkReply(listReply);
            }
        }catch (Exception e){
            e.printStackTrace();
            list=null;
        }
        return list;
    }

    /**
     * 根据评论id查询器对应的所有回复对话数据
     * @param remark_id  评论id
     * @return 回复对话集合
     */
    public List<MessageRemarkReply> selectReplyDataByPrimaryKey(Integer remark_id){
        List<MessageRemarkReply> list =new ArrayList <MessageRemarkReply>();
        try {
            list=messageRemarkReplyMapper.selectReplyDataByPrimaryKey(remark_id);
        }catch (Exception e){
            e.printStackTrace();
            list=null;
        }
        return list;
    }
}
