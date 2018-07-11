package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;

import java.util.List;

public interface UserService {
    /**
     * 登录
     */
    User login(String username, String password);
    /**
     * 查询所有用户
     */
    List<User> findAll();
    /**
     * 根据条件查询用户
     */
    List<User> findByUser(UserExample example);
    /**
     * 根据id查询
     */
    User findById(String id);
    /**
     * 添加
     */
    void add(User user);
    /**
     * 修改
     */
    void update(User user);
    /**
     * 删除
     */
    void delete(String id);
}
