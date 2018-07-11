package com.baizhi.service;

import com.baizhi.entity.Shop;
import com.baizhi.entity.ShopExample;

import java.util.List;

public interface ShopService {
    /**
     * 查询所有商铺
     */
    List<Shop> findAll();
    /**
     * 根据条件查询商铺
     */
    List<Shop> findByShop(ShopExample example, Integer pageNow, Integer pageSize);
    /**
     * 根据id查询商铺
     */
    Shop findById(String id);
    /**
     * 添加商铺
     */
    void add(Shop shop);
    /**
     * 修改商铺
     */
    void update(Shop shop);
    /**
     * 删除商铺
     */
    void delete(String id);
}
