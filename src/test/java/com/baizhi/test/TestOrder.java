package com.baizhi.test;

import com.baizhi.util.NoUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOrder {
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;
    @Test
    public void testNo(){
        String no = NoUtil.getNo();
        System.out.println(no);
    }
    @Test
    public void testDate(){
        System.out.println(Date.valueOf("1996-11-01"));
    }
}
