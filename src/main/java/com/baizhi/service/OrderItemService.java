package com.baizhi.service;

import com.baizhi.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    /**
     * 根据订单id查询
     */
    List<OrderItem> findByOrderId(String orderId);
    /**
     * 根据id查询
     */
    OrderItem findById(String id);
    /**
     * 添加
     */
    void add(OrderItem orderItem);
    /**
     * 删除
     */
    void delete(String id);
}
