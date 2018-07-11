package com.baizhi.service;

import com.baizhi.dao.*;
import com.baizhi.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service("goodsService")
@Transactional
public class GoodsServiceImpl implements GoodsService{
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private ShopMapper shopMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private GoodsCategoryService goodsCategoryService;
    @Resource
    private ThemeMapper themeMapper;
    @Resource
    private PictureMapper pictureMapper;
    @Override
    public List<HashMap<String, Object>> findAll() {
        List<Goods> goods = goodsMapper.selectByExample(new GoodsExample());
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        for (Goods good : goods) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("goods",good);
            Shop shop = shopMapper.selectByPrimaryKey(good.getSeller());
            map.put("shop",shop);
            Theme theme = themeMapper.selectByPrimaryKey(good.getThemeId());
            map.put("theme",theme);
            PictureExample pictureExample = new PictureExample();
            pictureExample.createCriteria().andGoodsIdEqualTo(good.getId());
            List<Picture> pictures = pictureMapper.selectByExample(pictureExample);
            map.put("pictures",pictures);
            List<GoodsCategoryKey> category = goodsCategoryService.findByGoodsId(good.getId());
            map.put("category", category);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<HashMap<String, Object>> findByShops(GoodsExample example) {
        List<Goods> goods = goodsMapper.selectByExample(example);
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        for (Goods good : goods) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("goods",good);
            Shop shop = shopMapper.selectByPrimaryKey(good.getSeller());
            map.put("shop",shop);
            Theme theme = themeMapper.selectByPrimaryKey(good.getThemeId());
            map.put("theme",theme);
            PictureExample pictureExample = new PictureExample();
            pictureExample.createCriteria().andGoodsIdEqualTo(good.getId());
            List<Picture> pictures = pictureMapper.selectByExample(pictureExample);
            map.put("pictures",pictures);
            List<GoodsCategoryKey> category = goodsCategoryService.findByGoodsId(good.getId());
            map.put("category", category);
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String, Object> findById(String id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goods",goods);
        Shop shop = shopMapper.selectByPrimaryKey(goods.getSeller());
        map.put("shop",shop);
        Theme theme = themeMapper.selectByPrimaryKey(goods.getThemeId());
        map.put("theme",theme);
        PictureExample pictureExample = new PictureExample();
        pictureExample.createCriteria().andGoodsIdEqualTo(goods.getId());
        List<Picture> pictures = pictureMapper.selectByExample(pictureExample);
        map.put("pictures",pictures);
        List<GoodsCategoryKey> category = goodsCategoryService.findByGoodsId(goods.getId());
        map.put("category", category);
        return map;
    }

    @Override
    public void add(Goods goods, String[] category) {
        goods.setId(UUID.randomUUID().toString());
        goods.setPutTime(new Date());
        goodsMapper.insertSelective(goods);
        goodsCategoryService.add(goods.getId(),category);
    }

    @Override
    public void update(Goods goods, String[] category) {
        goodsMapper.updateByPrimaryKeySelective(goods);
        goodsCategoryService.deleteByGoodsId(goods.getId());
        goodsCategoryService.add(goods.getId(),category);
    }

    @Override
    public void delete(String id) {
        goodsCategoryService.deleteByGoodsId(id);
        goodsMapper.deleteByPrimaryKey(id);
    }
}
