package com.hafiz.www.service;


import com.hafiz.www.po.MessageRemarkReply;

/**
 * 留言墙评论回复的service接口
 */
public interface MessageRemarkReplayService {

    /**
     * 保存留言墙评论回复数据
     * @param record  保存数据
     * @return  保存状态
     */
    int insertSelective(MessageRemarkReply record);
}
