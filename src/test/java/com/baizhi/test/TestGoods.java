package com.baizhi.test;

import com.baizhi.dao.GoodsMapper;
import com.baizhi.entity.Goods;
import com.baizhi.entity.GoodsExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Clob;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGoods {
    @Resource
    private GoodsMapper goodsMapper;
    @Test
    public void testSelectAll(){
        List<Goods> goods = goodsMapper.selectByExample(new GoodsExample());
        for (Goods good : goods) {
            System.out.println(good.getInstructions());
        }
    }
}
