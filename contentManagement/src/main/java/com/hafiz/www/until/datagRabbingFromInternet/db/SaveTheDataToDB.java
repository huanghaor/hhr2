package com.hafiz.www.until.datagRabbingFromInternet.db;

import com.hafiz.www.mapper.TechnologyModuleMapper;
import com.hafiz.www.po.TechnologyModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保存数据至数据库
 */
@Service
public class SaveTheDataToDB {
    @Autowired
    public TechnologyModuleMapper technologyModuleMapper;


    /**
     * 保存值技术文章
     * @param list
     * @return
     */
    public int SaveTheArticle(List<TechnologyModule> list){
        int isSuccess = technologyModuleMapper.insertSelectiveList(list);
        return isSuccess;
    }
}
