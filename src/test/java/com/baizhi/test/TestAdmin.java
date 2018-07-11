package com.baizhi.test;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAdmin {
    @Resource
    private AdminService adminService;
    @Test
    public void addTest(){
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("123456");
        admin.setPowerLevel(0);
        adminService.add(admin);
    }
    @Test
    public void loginTest(){
        Admin admin = adminService.login("admin", "123456");
        System.out.println(admin);
    }
}
