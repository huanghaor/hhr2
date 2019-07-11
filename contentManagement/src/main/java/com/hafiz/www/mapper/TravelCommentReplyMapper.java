package com.hafiz.www.mapper;

import com.hafiz.www.po.TravelCommentReply;

import java.util.List;

public interface TravelCommentReplyMapper {
    int deleteByPrimaryKey(Integer reply_id);

    int insert(TravelCommentReply record);

    int insertSelective(TravelCommentReply record);

    TravelCommentReply selectByPrimaryKey(Integer reply_id);

    int updateByPrimaryKeySelective(TravelCommentReply record);

    int updateByPrimaryKeyWithBLOBs(TravelCommentReply record);

    int updateByPrimaryKey(TravelCommentReply record);

    /**
     * 根据字段进行查询
     * @param record 相关字段条件
     * @return list集合
     */
    List<TravelCommentReply> selectReplyData(TravelCommentReply record);


    /**
     * 根据评论id查询对应回复数据
     *
     */
    List<TravelCommentReply> getTheTravelCommentReplyByC_id(Integer c_id);

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
    int selectMaxWithreply_addid_with_two_person(TravelCommentReply record);
}