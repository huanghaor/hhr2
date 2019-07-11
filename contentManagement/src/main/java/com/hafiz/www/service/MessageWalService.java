package com.hafiz.www.service;

import com.hafiz.www.po.MessageWal;
import com.hafiz.www.po.PublicPagingQuery;

import java.util.List;

/**
 * 留言墙 services接口
 */
public interface MessageWalService {

    /**
     * 保存留言
     * @param record  上传数据
     * @return 返回数据id
     */
    int insertSelective(MessageWal record);

    /**
     * 根据数据id查询对应实体信息
     * @param w_id  数据id
     * @return  实体对象
     */
    MessageWal selectByPrimaryKey(Integer w_id);

    /**
     * 查询留言墙数据
     * @param publicPagingQuery 查询条件
     * @return  对象
     */
   void selectMessageData(PublicPagingQuery<MessageWal> publicPagingQuery);

    /**
     * 获取数据总数
     * @param publicPagingQuery 数据状态：1表示再用；0表示删除
     * @return  总数
     */
    int getCount(PublicPagingQuery publicPagingQuery);
    //int getCount(String status);

    /**
     * 跟新数据
     * @param record 数据
     * @return
     */
    int updateByPrimaryKeySelective(MessageWal record);

    /**
     * 删除我的留言
     *  @param w_id  数据id
     * @return  删除状态
     */
    int deleteByPrimaryKey(Integer w_id);
}
