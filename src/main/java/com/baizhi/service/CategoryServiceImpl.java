package com.baizhi.service;

import com.baizhi.dao.CategoryMapper;
import com.baizhi.dao.GoodsCategoryMapper;
import com.baizhi.entity.*;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;
    @Override
    public List<Category> findAllCategory() {
        CategoryExample example = new CategoryExample();
        List<Category> categories = categoryMapper.selectByExample(new CategoryExample());
        for (Category category : categories) {
            Category parent = categoryMapper.selectByPrimaryKey(category.getPid());
            if(parent!=null){
                category.setParent(parent.getName());
            }
        }
        return categories;
    }

    @Override
    public List<Category> findByCategory(CategoryExample example) {
        List<Category> categories = categoryMapper.selectByExample(example);
        for (Category category : categories) {
            Category parent = categoryMapper.selectByPrimaryKey(category.getPid());
            if(parent!=null){
                category.setParent(parent.getName());
            }
        }
        return categories;
    }

    @Override
    public Category findById(String id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        Category parent = categoryMapper.selectByPrimaryKey(category.getPid());
        if(parent!=null){
            category.setParent(parent.getName());
        }
        return category;
    }

    @Override
    public void add(Category category) {
        category.setId(UUID.randomUUID().toString());
        category.setCreateTime(new Date());
        category.setPv(0);
        categoryMapper.insertSelective(category);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void delete(String id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        Category parent = categoryMapper.selectByPrimaryKey(category.getPid());
        if(parent==null){
            CategoryExample example = new CategoryExample();
            example.createCriteria().andPidEqualTo(id);
            List<Category> categories = categoryMapper.selectByExample(example);
            if(categories.size()==0){
                categoryMapper.deleteByPrimaryKey(id);
            }else{
                return;
            }
        }else{
            GoodsCategoryExample example = new GoodsCategoryExample();
            example.createCriteria().andCategoryIdEqualTo(id);
            List<GoodsCategoryKey> list = goodsCategoryMapper.selectByExample(example);
            if(list.size()==0){
                categoryMapper.deleteByPrimaryKey(id);
            }else{
                return;
            }
        }
    }
}
