package com.baizhi.dao;

import com.baizhi.entity.Goods;

import java.util.List;

public interface LuceneDAO {
    /**
     * 取得所有商品
     * @return
     */
    List<Goods> getAllGoods(String keyword) throws Exception;
    /**
     * 添加商品
     */
    void add(Goods goods);
    /**
     * 修改商品
     */
    void update(Goods goods);
    /**
     * 删除商品
     */
    void delete(String id);
}
