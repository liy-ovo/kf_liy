package com.baizhi.service;

import com.baizhi.dao.AdminMapper;
import com.baizhi.dao.GoodsMapper;
import com.baizhi.dao.ShopMapper;
import com.baizhi.entity.GoodsExample;
import com.baizhi.entity.Shop;
import com.baizhi.entity.ShopExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("shopService")
@Transactional
public class ShopServiceImpl implements ShopService{
    @Resource
    private ShopMapper shopMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private AdminMapper adminMapper;
    @Override
    public List<Shop> findAll() {
        List<Shop> shops = shopMapper.selectByExample(new ShopExample());
        return shops;
    }

    @Override
    public List<Shop> findByShop(ShopExample example, Integer pageNow, Integer pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        List<Shop> shops = shopMapper.selectByExample(example);
        return shops;
    }

    @Override
    public Shop findById(String id) {
        return shopMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Shop shop) {
        shop.setId(UUID.randomUUID().toString());
        shopMapper.insertSelective(shop);
    }

    @Override
    public void update(Shop shop) {
        shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Override
    public void delete(String id) {
        GoodsExample example = new GoodsExample();
        example.createCriteria().andSellerEqualTo(id);
        goodsMapper.deleteByExample(example);
        adminMapper.deleteByPrimaryKey(shopMapper.selectByPrimaryKey(id).getAdminId());
        shopMapper.deleteByPrimaryKey(id);
    }
}
