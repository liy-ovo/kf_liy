package com.baizhi.service;

import com.baizhi.entity.Address;

import java.util.List;

public interface AddressService {
    /**
     * 根据用户id查询地址
     */
    List<Address> findByUserId(String id);
    /**
     * 根据地址id查询
     */
    Address findById(String id);
    /**
     * 添加
     */
    void add(Address address);
    /**
     * 修改
     */
    void update(Address address);
    /**
     * 删除
     */
    void delete(String id);
}
