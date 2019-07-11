package com.hafiz.www.service;

import com.hafiz.www.po.EssayDiary;
import com.hafiz.www.po.EssayDiaryType;
import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.until.JsonResult;

import java.util.List;

/**
 * 随笔日记service接口
 */
public interface EssayDiaryService {

    /**
     * 初始化日记类别列表数据
     * @return
     */
    JsonResult initTheDiaryTypeList();

    /**
     * 添加日记类别数据
     * @param essayDiaryType 添加数据
     * @return 数据库之行状态
     */
    JsonResult addTheDiaryType(EssayDiaryType essayDiaryType);

    /**
     * 添加日记数据
     * @param essayDiary 日记数据
     * @return 保存状态
     */
    JsonResult addEssayDiaryData(EssayDiary essayDiary);

    /**
     * 初始化日记数据
     * @param publicPagingQuery
     * @return
     */
    JsonResult initEssayDiaryData(PublicPagingQuery publicPagingQuery);

    /**
     * 添加阅读量
     */
    void addTheReadNum(String e_id);

    /**
     * 管理员日记条件查询
     * @param essayDiary
     * @return
     */
    JsonResult selectEssayDiaryDataByManager(EssayDiary essayDiary);
    /**
     * 管理员日记设置为精帖
     * @param essayDiary
     * @return
     */
    JsonResult updateEssayDiaryDataByManager(EssayDiary essayDiary);
    /**
     * 管理员日记批量删除
     * @param essayDiary
     * @return
     */
    JsonResult deleteEssayDiaryDataByManager(EssayDiary essayDiary);
}
