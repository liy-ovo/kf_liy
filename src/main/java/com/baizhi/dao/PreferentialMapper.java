package com.baizhi.dao;

import com.baizhi.entity.Preferential;
import com.baizhi.entity.PreferentialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PreferentialMapper {
    long countByExample(PreferentialExample example);

    int deleteByExample(PreferentialExample example);

    int deleteByPrimaryKey(String id);

    int insert(Preferential record);

    int insertSelective(Preferential record);

    List<Preferential> selectByExampleWithBLOBs(PreferentialExample example);

    List<Preferential> selectByExample(PreferentialExample example);

    Preferential selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Preferential record, @Param("example") PreferentialExample example);

    int updateByExampleWithBLOBs(@Param("record") Preferential record, @Param("example") PreferentialExample example);

    int updateByExample(@Param("record") Preferential record, @Param("example") PreferentialExample example);

    int updateByPrimaryKeySelective(Preferential record);

    int updateByPrimaryKeyWithBLOBs(Preferential record);

    int updateByPrimaryKey(Preferential record);
}