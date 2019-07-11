package com.hafiz.www.service.impl;

import com.hafiz.www.mapper.MessageWalMapper;
import com.hafiz.www.po.MessageWal;
import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.service.MessageWalService;
import com.hafiz.www.shiro.SessionUtils;
import com.hafiz.www.until.GetTheTimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageWalImpl implements MessageWalService {

    @Autowired
    private MessageWalMapper messageWalMapper;

    /**
     * 保存数据
     * @param record  上传数据
     * @return
     */
    public int insertSelective(MessageWal record) {
        record.setW_status("1");//未删除
        //判断当前用户是否登陆
        int data=0;
        if(SessionUtils.isLoggedIn()){
            record.setW_uid(SessionUtils.getLoginUserId());//用户id
            record.setW_addtime(new GetTheTimeStamp().getCurrentTime());//当前时间
            data = messageWalMapper.insertSelective(record);//保存成功会返回1
        }else{
            data=-1;
        }

        return data;
    }

    /**
     * 查询留言墙数据
     * @param publicPagingQuery 查询条件
     * @return 对象集合
     */
    public void selectMessageData(PublicPagingQuery publicPagingQuery) {
        publicPagingQuery.setRows(messageWalMapper.selectMessageData(publicPagingQuery));
        publicPagingQuery.setTotal(messageWalMapper.getCount(publicPagingQuery));
    }

    /**
     * 获取数据总数
     * @param publicPagingQuery 数据状态：1表示再用；0表示删除
     * @return 总数
     */

    public int getCount(PublicPagingQuery publicPagingQuery) {
        int count =  messageWalMapper.getCount(publicPagingQuery);
        return count;
    }

    /**
     * 根据数据id查询对应信息
     * @param w_id  数据id
     * @return 实体
     */
    public MessageWal selectByPrimaryKey(Integer w_id) {
        return messageWalMapper.selectByPrimaryKey(w_id);
    }

    /**
     * 跟新数据
     * @param record 数据
     * @return 跟新状态
     */
    public int updateByPrimaryKeySelective(MessageWal record) {
        return messageWalMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 删除我的留言
     * @param w_id  数据id
     * @return  删除状态
     */
    public int deleteByPrimaryKey(Integer w_id) {
        return messageWalMapper.deleteByPrimaryKey(w_id);
    }


}
