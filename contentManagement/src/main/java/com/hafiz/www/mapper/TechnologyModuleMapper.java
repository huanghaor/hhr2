package com.hafiz.www.mapper;

import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.po.TechnologyModule;
import com.hafiz.www.until.JsonResult;

import java.util.List;

public interface TechnologyModuleMapper {
    int deleteByPrimaryKey(Integer m_id);

    int insert(TechnologyModule record);

    int insertSelective(TechnologyModule record);

    int insertSelectiveList(List<TechnologyModule> record);

    TechnologyModule selectByPrimaryKey(Integer m_id);

    int updateByPrimaryKeySelective(TechnologyModule record);

    int updateByPrimaryKeyWithBLOBs(TechnologyModule record);

    int updateByPrimaryKey(TechnologyModule record);

    /**
     * 查询最新发布的10条数据
     * @return
     */
    List<TechnologyModule> selectTechnologyModuleDataTopNewPublish();


    /**
     * 根据条件分页查询相关数据
     * @param publicPagingQuery 分页实体
     * @return list集合
     */
    List<TechnologyModule> selectTechnologyModuleAllData(PublicPagingQuery publicPagingQuery);

    int getCount(PublicPagingQuery publicPagingQuery);

    /**
     * 管理员查询
     * @param technologyModule
     * @return
     */
    List<TechnologyModule> selectArticleDataByManager(TechnologyModule technologyModule);

    /**
     * 管理员技术文章批量删除
     * @param technologyModule
     * @return
     */
    int deleteArticleDataByManager(TechnologyModule technologyModule);
}