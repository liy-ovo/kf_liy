package com.baizhi.test;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {
    @Resource
    private UserService userService;
    @Test
    public void loginTest(){
        User user1 = userService.login("user4", "123456");
        User user2 = userService.login("1111", "1111");
        System.out.println(user1);
        System.out.println(user2);
    }
}
