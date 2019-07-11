package com.hafiz.www.mapper;

import com.hafiz.www.po.MessageRemarkReply;

import java.util.List;

public interface MessageRemarkReplyMapper {
    int deleteByPrimaryKey(Integer reply_id);

    int insert(MessageRemarkReply record);

    int insertSelective(MessageRemarkReply record);

    MessageRemarkReply selectByPrimaryKey(Integer reply_id);

    int updateByPrimaryKeySelective(MessageRemarkReply record);

    int updateByPrimaryKeyWithBLOBs(MessageRemarkReply record);

    int updateByPrimaryKey(MessageRemarkReply record);

    /**
     * 根据字段进行查询
     * @param record 相关字段条件
     * @return list集合
     */
    List<MessageRemarkReply> selectReplyData(MessageRemarkReply record);

    /**
     * 根据留言id查询
     * @param remark_id 留言id
     * @return list集合
     */
    List<MessageRemarkReply> selectReplyDataByPrimaryKey(Integer remark_id);

    /**
     * 查询表中Reply_sameid_with_two_person字段的最大值
     * @return 数值
     */
    int selectMaxWithReply_sameid_with_two_person();

    /**
     * 根据reply_from_uid，reply_to_uid，remark_id 查询其reply_addid_with_two_person字段的最大值
     * @param record 查询条件
     * @return 数值
     */
    int selectMaxWithreply_addid_with_two_person(MessageRemarkReply record);
}