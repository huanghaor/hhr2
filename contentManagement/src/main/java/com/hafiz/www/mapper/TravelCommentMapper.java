package com.hafiz.www.mapper;

import com.hafiz.www.po.TravelComment;

import java.util.List;

public interface TravelCommentMapper {
    int deleteByPrimaryKey(Integer c_id);

    int insert(TravelComment record);

    int insertSelective(TravelComment record);

    TravelComment selectByPrimaryKey(Integer c_id);

    int updateByPrimaryKeySelective(TravelComment record);

    int updateByPrimaryKey(TravelComment record);

    /**
     * 初始化所有评论数据
     * @param tc_id 查询id
     * @return list集合
     */
    List<TravelComment> selectCommentAndReplyData(Integer tc_id);
}