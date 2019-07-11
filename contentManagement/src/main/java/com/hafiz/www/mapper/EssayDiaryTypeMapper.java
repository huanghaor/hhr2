package  com.hafiz.www.mapper;


import com.hafiz.www.po.EssayDiaryType;

import java.util.List;

public interface EssayDiaryTypeMapper {
    int deleteByPrimaryKey(Integer et_id);

    int insert(EssayDiaryType record);

    int insertSelective(EssayDiaryType record);

    EssayDiaryType selectByPrimaryKey(Integer et_id);

    int updateByPrimaryKeySelective(EssayDiaryType record);

    int updateByPrimaryKey(EssayDiaryType record);

    List<EssayDiaryType> selectAllData();
}