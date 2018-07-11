package com.baizhi.service;

import com.baizhi.entity.Picture;

import java.util.List;

public interface PictureService {
    /**
     * 根据商品id查询
     */
    List<Picture> findByGoodsId(String id);
    /**
     * 根据图片id查询
     */
    Picture findById(String id);
    /**
     * 添加
     */
    void add(Picture picture);
    /**
     * 修改
     */
    void update(Picture picture);
    /**
     * 删除
     */
    void delete(String id);
}
