package com.baizhi.dao;

import com.baizhi.entity.AssessType;
import com.baizhi.entity.AssessTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssessTypeMapper {
    long countByExample(AssessTypeExample example);

    int deleteByExample(AssessTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssessType record);

    int insertSelective(AssessType record);

    List<AssessType> selectByExample(AssessTypeExample example);

    AssessType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssessType record, @Param("example") AssessTypeExample example);

    int updateByExample(@Param("record") AssessType record, @Param("example") AssessTypeExample example);

    int updateByPrimaryKeySelective(AssessType record);

    int updateByPrimaryKey(AssessType record);
}