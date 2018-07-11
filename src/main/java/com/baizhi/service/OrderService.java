package com.baizhi.service;

import com.baizhi.entity.Order;
import com.baizhi.entity.OrderExample;

import java.util.List;

public interface OrderService {
    /**
     * 查询所有
     */
    List<Order> findAll();
    /**
     * 根据条件查询
     */
    List<Order> findByOrder(OrderExample example);
    /**
     * 根据id查询
     */
    Order findById(String id);
    /**
     * 添加
     */
    void add(Order order);
    /**
     * 修改
     */
    void update(Order order);
    /**
     * 删除
     */
    void delete(String id);
}
