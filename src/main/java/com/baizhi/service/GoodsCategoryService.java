package com.baizhi.service;

import com.baizhi.entity.GoodsCategoryKey;

import java.util.List;

public interface GoodsCategoryService {
    /**
     * 根据分类id查询
     */
    List<GoodsCategoryKey> findByCategoryId(String id);
    /**
     * 根据商品id查询
     */
    List<GoodsCategoryKey> findByGoodsId(String id);
    /**
     * 添加
     */
    void add(String goodsId, String[] categoryId);
    /**
     * 根据商品id删除
     */
    void deleteByGoodsId(String id);
}
