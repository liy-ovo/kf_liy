package com.baizhi.service;

import com.baizhi.dao.GoodsCategoryMapper;
import com.baizhi.entity.GoodsCategoryExample;
import com.baizhi.entity.GoodsCategoryKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("goodsCategoryService")
@Transactional
public class GoodsCategoryServiceImpl implements GoodsCategoryService{
    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;
    @Override
    public List<GoodsCategoryKey> findByCategoryId(String id) {
        GoodsCategoryExample example = new GoodsCategoryExample();
        example.createCriteria().andCategoryIdEqualTo(id);
        List<GoodsCategoryKey> goodsCategoryKeys = goodsCategoryMapper.selectByExample(example);
        return goodsCategoryKeys;
    }

    @Override
    public List<GoodsCategoryKey> findByGoodsId(String id) {
        GoodsCategoryExample example = new GoodsCategoryExample();
        example.createCriteria().andGoodsIdEqualTo(id);
        List<GoodsCategoryKey> goodsCategoryKeys = goodsCategoryMapper.selectByExample(example);
        return goodsCategoryKeys;
    }

    @Override
    public void add(String goodsId, String[] categoryId) {
        if(categoryId==null || categoryId.length==0){
            return;
        }
        for (String s : categoryId) {
            GoodsCategoryKey goodsCategoryKey = new GoodsCategoryKey();
            goodsCategoryKey.setCategoryId(s);
            goodsCategoryKey.setGoodsId(goodsId);
            goodsCategoryMapper.insertSelective(goodsCategoryKey);
        }
    }

    @Override
    public void deleteByGoodsId(String id) {
        GoodsCategoryExample goodsCategoryExample = new GoodsCategoryExample();
        goodsCategoryExample.createCriteria().andGoodsIdEqualTo(id);
        goodsCategoryMapper.deleteByExample(goodsCategoryExample);
    }
}
