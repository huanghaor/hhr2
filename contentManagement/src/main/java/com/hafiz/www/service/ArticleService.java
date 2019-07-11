package com.hafiz.www.service;

import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.po.TechnologyModule;
import com.hafiz.www.po.TypeOfArticle;
import com.hafiz.www.until.JsonResult;

import java.util.List;

/**
 * 技术文章相关接口
 */
public interface ArticleService {

    /**
     * 获取技术文章分类列表
     * @return LIST列表
     */
    List<TypeOfArticle> getTheTypeOfArtileList();

    /**
     * 保存新增文章
     * @param technologyModule  上传数据
     * @return 返回保存的当前id
     */
    int insertSelective(TechnologyModule technologyModule);

    /**
     * 初始化根据条件查询数据
     * @param publicPagingQuery
     * @return
     */
    JsonResult selectTechnoDataByConditon(PublicPagingQuery publicPagingQuery);

    /**
     * 保存技术文章数据
     * @param list
     * @return
     */
    int SaveTheArticle(List<TechnologyModule> list);

    /**
     * 管理员条件查询
     * @param technologyModule
     * @return
     */
    JsonResult selectArticleDataByManager(TechnologyModule technologyModule);
    /**
     * 管理员技术文章设置为精帖
     * @param technologyModule
     * @return
     */
    JsonResult updateArticleDataByManager(TechnologyModule technologyModule);
    /**
     * 管理员技术文章批量删除
     * @param technologyModule
     * @return
     */
    JsonResult deleteArticleDataByManager(TechnologyModule technologyModule);
}
