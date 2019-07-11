package com.hafiz.www.mapper;

import com.hafiz.www.po.TechnologyModuleEnclosure;

import java.util.List;

public interface TechnologyModuleEnclosureMapper {
    int deleteByPrimaryKey(Integer e_id);

    int insert(List<TechnologyModuleEnclosure> record);

    int insertSelective(TechnologyModuleEnclosure record);

    List<TechnologyModuleEnclosure> selectByPrimaryKey(Integer e_id);

    int updateByPrimaryKeySelective(TechnologyModuleEnclosure record);

    int updateByPrimaryKey(TechnologyModuleEnclosure record);
}