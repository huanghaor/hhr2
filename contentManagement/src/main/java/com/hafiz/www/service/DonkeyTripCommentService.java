package com.hafiz.www.service;

import com.hafiz.www.po.TravelComment;
import com.hafiz.www.po.TravelCommentReply;

import java.util.List;

/**
 * 游记评论service接口
 */
public interface DonkeyTripCommentService {

    /**
     * 保存评论
     * @param record 保存数据
     * @return 返回保存状态
     */
    int insertSelective(TravelComment record);

    /**
     * 根据游记数据id查询器对应的所有评论和评论回复数据
     * @param tc_id 游记数据id
     * @return List集合数据
     */
    List<TravelComment> selectCommentAndReplyData(Integer tc_id);

    /**
     * 保存评论回复数据
     * @param travelCommentReply 回复数据
     * @return 保存状态
     */
    int saveTheTravelReply(TravelCommentReply travelCommentReply);
}
