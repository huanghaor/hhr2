package com.hafiz.www.mapper;

import com.hafiz.www.po.TypeOfArticle;

import java.util.List;

public interface TypeOfArticleMapper {
    int deleteByPrimaryKey(Integer a_id);

    int insert(TypeOfArticle record);

    int insertSelective(TypeOfArticle record);

    TypeOfArticle selectByPrimaryKey(Integer a_id);

    int updateByPrimaryKeySelective(TypeOfArticle record);

    int updateByPrimaryKey(TypeOfArticle record);

    List<TypeOfArticle> getTheTypeOfArtileList();
}