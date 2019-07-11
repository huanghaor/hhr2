package com.hafiz.www.mapper;

import com.hafiz.www.po.TravelPictureUrl;

import java.util.List;

public interface TravelPictureUrlMapper {
    int deleteByPrimaryKey(Integer p_id);

    int insert(List<TravelPictureUrl> record);

    int insertSelective(TravelPictureUrl record);

    TravelPictureUrl selectByPrimaryKey(Integer p_id);

    int updateByPrimaryKeySelective(TravelPictureUrl record);

    int updateByPrimaryKey(TravelPictureUrl record);
    /**
     * 根据驴行数据id查询其对应图片保存路径
     * @param T_ID 留言id
     * @return list集合
     */
    List<TravelPictureUrl> selectTravleIDDataByPrimaryKey(Integer T_ID);
}