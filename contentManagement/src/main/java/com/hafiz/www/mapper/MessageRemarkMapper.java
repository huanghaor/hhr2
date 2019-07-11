package com.hafiz.www.mapper;

import com.hafiz.www.po.MessageRemark;

import java.util.List;

public interface MessageRemarkMapper {
    int deleteByPrimaryKey(Integer remark_id);

    int insert(MessageRemark record);

    /**
     * 保存评论信息
     * @param record 评论信息
     * @return 返回处理状态
     */
    int insertSelective(MessageRemark record);

    MessageRemark selectByPrimaryKey(Integer remark_id);

    int updateByPrimaryKeySelective(MessageRemark record);

    int updateByPrimaryKeyWithBLOBs(MessageRemark record);

    int updateByPrimaryKey(MessageRemark record);

    /**
     * 根据留言墙数据id统计该id下所有的评论数
     * @param remark_message_id 留言墙数据id
     * @return  统计数量
     */
    int getCount(String remark_message_id);

    List<MessageRemark> selectRemarkAndReplyData(Integer remark_message_id);
}