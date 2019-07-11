package com.hafiz.www.service;


import com.hafiz.www.po.NavigationBarRecord;
import com.hafiz.www.until.JsonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 主页相关接口
 */
public interface IndexService {
    /**
     * 获取主页导航
     * @return
     */
    List<NavigationBarRecord> getIndexNavList();

    /**
     * 管理员获取主页导航
     * @return
     */
    JsonResult getIndexNavListsByManager();

    /**
     * 初始化首页页面数据
     * @return
     */
    JsonResult initIndexData(HttpServletRequest req);

    /**
     * 导航栏修改数据
     * @param navigationBarRecord
     * @return
     */
    JsonResult updateNavigation(NavigationBarRecord navigationBarRecord);

    /**
     * 新增导航栏
     * @param navigationBarRecord
     * @return
     */
    JsonResult insertNavigation(NavigationBarRecord navigationBarRecord);
}
