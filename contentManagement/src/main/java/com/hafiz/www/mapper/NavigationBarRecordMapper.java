package com.hafiz.www.mapper;

import com.hafiz.www.po.NavigationBarRecord;

import java.util.List;

public interface NavigationBarRecordMapper {
    int deleteByPrimaryKey(Integer n_id);

    int insert(NavigationBarRecord record);

    int insertSelective(NavigationBarRecord record);

    NavigationBarRecord selectByPrimaryKey(Integer n_id);

    List<NavigationBarRecord> getIndexNavLists(NavigationBarRecord navigationBarRecord);

    int updateByPrimaryKeySelective(NavigationBarRecord record);

    int updateByPrimaryKey(NavigationBarRecord record);

    List<NavigationBarRecord> getIndexNavListsByManager();
}