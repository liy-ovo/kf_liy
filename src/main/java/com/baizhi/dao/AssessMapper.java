package com.baizhi.dao;

import com.baizhi.entity.Assess;
import com.baizhi.entity.AssessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssessMapper {
    long countByExample(AssessExample example);

    int deleteByExample(AssessExample example);

    int deleteByPrimaryKey(String id);

    int insert(Assess record);

    int insertSelective(Assess record);

    List<Assess> selectByExampleWithBLOBs(AssessExample example);

    List<Assess> selectByExample(AssessExample example);

    Assess selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Assess record, @Param("example") AssessExample example);

    int updateByExampleWithBLOBs(@Param("record") Assess record, @Param("example") AssessExample example);

    int updateByExample(@Param("record") Assess record, @Param("example") AssessExample example);

    int updateByPrimaryKeySelective(Assess record);

    int updateByPrimaryKeyWithBLOBs(Assess record);

    int updateByPrimaryKey(Assess record);
}