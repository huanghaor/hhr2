package com.hafiz.www.mapper;

import com.hafiz.www.po.MessageWal;
import com.hafiz.www.po.PublicPagingQuery;

import java.util.List;

public interface MessageWalMapper {
    int deleteByPrimaryKey(Integer w_id);

    int insert(MessageWal record);

    int insertSelective(MessageWal record);

    MessageWal selectByPrimaryKey(Integer w_id);

    int updateByPrimaryKeySelective(MessageWal record);

    int updateByPrimaryKey(MessageWal record);

    /**
     * 分页查询数据
     * @param publicPagingQuery
     * @return
     */
    List<MessageWal> selectMessageData(PublicPagingQuery publicPagingQuery);

    //int getCount(PublicPagingQuery publicPagingQuery);

    /**
     * 获取数据总数
     * @param publicPagingQuery
     * @return
     */
    int getCount(PublicPagingQuery publicPagingQuery);

    /**
     * 查询最新10条数据
     * @return
     */
    List<MessageWal> selecttMessageDataTopNewPublish();
}