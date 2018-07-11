package com.baizhi.Controller;

import com.baizhi.entity.Order;
import com.baizhi.service.OrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    @RequestMapping("/queryAllByPage")
    public List<Order> queryAllByPage(@RequestParam("page") Integer pageNow, @RequestParam("rows") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        return orderService.findAll();
    }
    @RequestMapping("/queryByUserAndByStatus")
    public List<Order> queryByUserAndByStatus(Integer status){
        return null;
    }
    @RequestMapping("/queryById")
    public Order queryById(String id){
        return orderService.findById(id);
    }
    @RequestMapping("/add")
    public void add(Order order){
        orderService.add(order);
    }
    @RequestMapping("/update")
    public void update(Order order){
        orderService.update(order);
    }
    @RequestMapping("/delete")
    public void delete(String id){
        orderService.delete(id);
    }
}
