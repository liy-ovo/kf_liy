package com.baizhi.dao;

import com.baizhi.entity.UserPreferential;
import com.baizhi.entity.UserPreferentialExample;
import com.baizhi.entity.UserPreferentialKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPreferentialMapper {
    long countByExample(UserPreferentialExample example);

    int deleteByExample(UserPreferentialExample example);

    int deleteByPrimaryKey(UserPreferentialKey key);

    int insert(UserPreferential record);

    int insertSelective(UserPreferential record);

    List<UserPreferential> selectByExample(UserPreferentialExample example);

    UserPreferential selectByPrimaryKey(UserPreferentialKey key);

    int updateByExampleSelective(@Param("record") UserPreferential record, @Param("example") UserPreferentialExample example);

    int updateByExample(@Param("record") UserPreferential record, @Param("example") UserPreferentialExample example);

    int updateByPrimaryKeySelective(UserPreferential record);

    int updateByPrimaryKey(UserPreferential record);
}