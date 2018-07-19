package com.baizhi.service;

import com.baizhi.entity.Tuser;

import java.util.List;

public interface TuserService {
    /**
     * 查询所有
     */
    List<Tuser> findAll();
    /**
     * 根据id查询
     */
    Tuser findById(String id);
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
