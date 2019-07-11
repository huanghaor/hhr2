package com.hafiz.www.mapper;

import com.hafiz.www.po.MessageWal;
import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.po.TravelCollection;

import java.util.List;

public interface TravelCollectionMapper {
    int deleteByPrimaryKey(Integer t_id);

    int insert(TravelCollection record);

    int insertSelective(TravelCollection record);

    TravelCollection selectByPrimaryKey(Integer t_id);

    int updateByPrimaryKeySelective(TravelCollection record);

    int updateByPrimaryKeyWithBLOBs(TravelCollection record);

    int updateByPrimaryKey(TravelCollection record);

    List<TravelCollection> selectMessageData(PublicPagingQuery publicPagingQuery);

    //int getCount(PublicPagingQuery publicPagingQuery);
    int getCount(PublicPagingQuery publicPagingQuery);

    List<TravelCollection> selectByT_fine_paste(PublicPagingQuery publicPagingQuery);

    List<TravelCollection> selectMessageDataByManager(TravelCollection travelCollection);
}