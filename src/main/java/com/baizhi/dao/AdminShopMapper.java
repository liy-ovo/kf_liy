package com.baizhi.dao;

import com.baizhi.entity.AdminShopExample;
import com.baizhi.entity.AdminShopKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminShopMapper {
    long countByExample(AdminShopExample example);

    int deleteByExample(AdminShopExample example);

    int deleteByPrimaryKey(AdminShopKey key);

    int insert(AdminShopKey record);

    int insertSelective(AdminShopKey record);

    List<AdminShopKey> selectByExample(AdminShopExample example);

    int updateByExampleSelective(@Param("record") AdminShopKey record, @Param("example") AdminShopExample example);

    int updateByExample(@Param("record") AdminShopKey record, @Param("example") AdminShopExample example);
}