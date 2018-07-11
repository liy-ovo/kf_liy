package com.baizhi.service;

import com.baizhi.entity.Category;
import com.baizhi.entity.CategoryExample;

import java.util.List;

public interface CategoryService {
    /**
     * 分页展示所有分类
     */
    List<Category> findAllCategory();
    /**
     * 条件查询分类
     */
    List<Category> findByCategory(CategoryExample example);
    /**
     * 根据id查询
     */
    Category findById(String id);
    /**
     * 添加分类
     */
    void add(Category category);
    /**
     * 修改分类
     */
    void update(Category category);
    /**
     * 删除分类
     */
    void delete(String id);
}
