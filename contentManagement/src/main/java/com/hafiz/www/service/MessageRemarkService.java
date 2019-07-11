package com.hafiz.www.service;

import com.hafiz.www.po.MessageRemark;

import java.util.List;

/**
 * 留言墙评论  services接口
 */
public interface MessageRemarkService {

    /**
     * 根据留言墙数据id统计该id下所有的评论数
     * @param remark_message_id 留言墙数据id
     * @return  统计数量
     */
    int getCount(String remark_message_id);

    /**
     * 保存评论信息
     * @param record  评论信息
     * @return 返回评论信息保存状态
     */
    int insertSelective(MessageRemark record);

    /**
     * 根据留言墙id 查询其对应所有的评论
     * @param remark_message_id 留言墙数据id
     * @return 评论集合
     */
    List<MessageRemark> selectRemarkAndReplyData(Integer remark_message_id);
}
