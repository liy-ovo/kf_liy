package com.baizhi.mongo;

import com.baizhi.entity.Tuser;

import java.util.List;

public interface TuserDAO {
    /**
     * 查询所有
     */
    List<Tuser> selectAll();
    /**
     * 根据主键查询
     */
    Tuser selectById(String id);
    /**
     * 添加
     */
    void add(Tuser tuser);
    /**
     * 修改
     */
    void update(Tuser tuser);
    /**
     * 删除
     */
    void delete(String id);
}
