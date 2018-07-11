package com.baizhi.service;

import com.baizhi.dao.OrderItemMapper;
import com.baizhi.entity.OrderItem;
import com.baizhi.entity.OrderItemExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("orderItemService")
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    @Resource
    private OrderItemMapper orderItemMapper;
    @Override
    public List<OrderItem> findByOrderId(String orderId) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public OrderItem findById(String id) {
        return orderItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(OrderItem orderItem) {
        orderItem.setId(UUID.randomUUID().toString());
        orderItem.setAddTime(new Date());
        orderItemMapper.insertSelective(orderItem);
    }

    @Override
    public void delete(String id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }
}
