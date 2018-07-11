package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.entity.AdminExample;

import java.util.List;

public interface AdminService {
    /**
     * 登录验证
     */
    Admin login(String username, String password);
    /**
     * 查询所有
     */
    List<Admin> findAll();
    /**
     * 根据条件查询
     */
    List<Admin> findByAdmin(AdminExample example);
    /**
     * 根据id查询
     */
    Admin findById(String id);
    /**
     * 添加
     */
    void add(Admin admin);
    /**
     * 修改
     */
    void update(Admin admin);
    /**
     * 删除
     */
    void delete(String id);
}
