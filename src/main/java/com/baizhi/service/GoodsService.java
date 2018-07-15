package com.baizhi.service;

import com.baizhi.entity.Goods;
import com.baizhi.entity.GoodsExample;
import com.baizhi.entity.Shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GoodsService {
    /**
     * 全文检索查询
     */
    List<Goods> findByLucene(String keyword);
    /**
     * 创建索引
     */
    void createDir();
    /**
     * 更新索引
     */
    void updateDocuments();
    /**
     * 查询所有商品
     */
    List<HashMap<String, Object>> findAll();
    /**
     * 条件查询商品
     */
    List<HashMap<String, Object>> findByShops(GoodsExample example);
    /**
     * 根据id查询商品
     */
    Map<String, Object> findById(String id);
    /**
     * 添加商品
     */
    void add(Goods goods, String[] category);
    /**
     * 修改商品
     */
    void update(Goods goods, String[] category);
    /**
     * 删除商品
     */
    void delete(String id);
}
