package com.hafiz.www.service;

import com.hafiz.www.po.MessageWal;
import com.hafiz.www.po.PublicPagingQuery;
import com.hafiz.www.po.TechnologyModule;
import com.hafiz.www.po.TravelCollection;
import com.hafiz.www.until.JsonResult;

import java.util.List;

/**
 * 驴行游记service接口
 */
public interface DonkeyTripService {

    /**
     * 保存新增游记
     * @param travelCollection  上传数据
     * @return 返回保存的当前id
     */
    int insertSelective(TravelCollection travelCollection);
    /**
     * 查询游记数据
     * @param publicPagingQuery 查询条件
     * @return  对象
     */
    void selectMessageData(PublicPagingQuery<TravelCollection> publicPagingQuery);

    /**
     * 根据数据id查询对象
     * @param t_id 数据id
     * @return
     */
    TravelCollection selectByPrimaryKey(Integer t_id);

    /**
     * 跟新数据
     * @param record 更新记录数据
     * @return 跟新状态：影响数据行数
     */
    int updateByPrimaryKeySelective(TravelCollection record);

    /**
     * 管理员根据条件查询数据
     * @param travelCollection
     * @return
     */
    JsonResult selectMessageDataByManager(TravelCollection travelCollection);

    /**
     * 删除游记
     * @param t_id
     * @return
     */
    JsonResult deleteByPrimaryKey(int t_id);

    /**
     * 管理员跟新数据
     * @param travelCollection
     * @return
     */
    JsonResult updateDonkeyTripById(TravelCollection travelCollection);
}
