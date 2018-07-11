package com.baizhi.Controller;

import com.baizhi.entity.Shop;
import com.baizhi.service.ShopService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/shop")
@ResponseBody
public class ShopController {
    @Resource
    private ShopService shopService;
    @RequestMapping("/queryAll")
    public List<Shop> queryAll(){
        List<Shop> list = shopService.findAll();
        Shop shop = new Shop();
        shop.setName("--无--");
        list.add(0,shop);
        return list;
    }
    @RequestMapping("/queryShops")
    public List<Shop> queryShops(@RequestParam("page") Integer pageNow,@RequestParam("rows") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        List<Shop> shops = shopService.findAll();
        return shops;
    }
    @RequestMapping("/queryById")
    public Shop queryById(String id){
        return shopService.findById(id);
    }
    @RequestMapping("/add")
    public void add(Shop shop){
        System.out.println(shop);
        shopService.add(shop);
    }
    @RequestMapping("/update")
    public void update(Shop shop){
        shopService.update(shop);
    }
    @RequestMapping("/delete")
    public void delete(String id){
        shopService.delete(id);
    }
    @RequestMapping("/deleteShops")
    public void deleteShops(String[] ids){
        for (String id : ids) {
            shopService.delete(id);
        }
    }
}
