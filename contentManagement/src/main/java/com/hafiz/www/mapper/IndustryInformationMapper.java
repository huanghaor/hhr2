package com.hafiz.www.mapper;

import com.hafiz.www.po.IndustryInformation;
import com.hafiz.www.po.PublicPagingQuery;

import java.util.List;

public interface IndustryInformationMapper {
    int deleteByPrimaryKey(Integer i_id);

    int deleteByManager(IndustryInformation industryInformation);

    int insert(IndustryInformation record);

    int insertSelective(List<IndustryInformation> record);

    int insertSelectiveList(List<IndustryInformation> record);

    IndustryInformation selectByPrimaryKey(Integer i_id);

    int updateByPrimaryKeySelective(IndustryInformation record);

    int updateByPrimaryKeyWithBLOBs(IndustryInformation record);

    int updateByPrimaryKey(IndustryInformation record);

    List<IndustryInformation> selectIndustryInformationDataByPage(PublicPagingQuery publicPagingQuery);

    List<IndustryInformation> selectIndustryInformationTopTenByTime();

    int getCount(PublicPagingQuery publicPagingQuery);

    List<IndustryInformation> selectIndustryInformationTopTenByFine_paste();
}