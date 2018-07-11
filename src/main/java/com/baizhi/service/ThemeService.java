package com.baizhi.service;

import com.baizhi.entity.Theme;
import com.baizhi.entity.ThemeExample;

import java.util.List;

public interface ThemeService {
    /**
     * 查询所有主题
     */
    List<Theme> findAll();
    /**
     * 条件查询
     */
    List<Theme> findByTheme(ThemeExample example);
    /**
     * 根据id查询
     */
    Theme findById(String id);
    /**
     * 添加
     */
    void add(Theme theme);
    /**
     * 修改
     */
    void update(Theme theme);
    /**
     * 删除
     */
    void delete(String id);
}
