package com.baizhi.test;

import com.baizhi.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCategoryService {
    @Resource
    private CategoryService categoryService;
    @Test
    public void testUpdate(){

    }
}
