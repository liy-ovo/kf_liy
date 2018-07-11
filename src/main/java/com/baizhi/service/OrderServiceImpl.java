package com.baizhi.service;

import com.baizhi.dao.OrderMapper;
import com.baizhi.entity.Order;
import com.baizhi.entity.OrderExample;
import com.baizhi.util.NoUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Override
    public List<Order> findAll() {
        return orderMapper.selectByExample(new OrderExample());
    }

    @Override
    public List<Order> findByOrder(OrderExample example) {
        return orderMapper.selectByExample(example);
    }

    @Override
    public Order findById(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Order order) {
        order.setId(UUID.randomUUID().toString());
        order.setCreateTime(new Date());
        order.setNo(NoUtil.getNo());
        orderMapper.insertSelective(order);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void delete(String id) {
        orderMapper.deleteByPrimaryKey(id);
    }
}
