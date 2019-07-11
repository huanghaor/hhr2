package com.hafiz.www.mapper;


import com.hafiz.www.po.EssayDiary;
import com.hafiz.www.po.PublicPagingQuery;

import java.util.List;

public interface EssayDiaryMapper {
    int deleteByPrimaryKey(Integer e_id);

    int insert(EssayDiary record);

    int insertSelective(EssayDiary record);

    EssayDiary selectByPrimaryKey(Integer e_id);

    int updateByPrimaryKeySelective(EssayDiary record);

    int updateByPrimaryKeyWithBLOBs(EssayDiary record);

    int updateByPrimaryKey(EssayDiary record);

    List<EssayDiary> selectEssayDiaryData(PublicPagingQuery publicPagingQuery);

    //int getCount(PublicPagingQuery publicPagingQuery);
    int getCount(PublicPagingQuery publicPagingQuery);

    List<EssayDiary> selectEssayDiaryDataTopThree();

    /**
     * 管理员查询
     * @param record
     * @return
     */
    List<EssayDiary> selectEssayDiaryDataByManager(EssayDiary record);

    /**
     * 管理员技术文章批量删除
     * @param record
     * @return
     */
    int deleteEssayDiaryDataByManager(EssayDiary record);
}