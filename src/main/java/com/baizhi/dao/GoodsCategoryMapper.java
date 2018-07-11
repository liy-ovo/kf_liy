package com.baizhi.dao;

import com.baizhi.entity.GoodsCategoryExample;
import com.baizhi.entity.GoodsCategoryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsCategoryMapper {
    long countByExample(GoodsCategoryExample example);

    int deleteByExample(GoodsCategoryExample example);

    int deleteByPrimaryKey(GoodsCategoryKey key);

    int insert(GoodsCategoryKey record);

    int insertSelective(GoodsCategoryKey record);

    List<GoodsCategoryKey> selectByExample(GoodsCategoryExample example);

    int updateByExampleSelective(@Param("record") GoodsCategoryKey record, @Param("example") GoodsCategoryExample example);

    int updateByExample(@Param("record") GoodsCategoryKey record, @Param("example") GoodsCategoryExample example);
}