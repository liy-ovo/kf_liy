package com.baizhi.Controller;

import com.baizhi.entity.Goods;
import com.baizhi.service.GoodsService;
import com.baizhi.util.POIUtil;
import com.github.pagehelper.PageHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    @RequestMapping("/queryAll")
    @ResponseBody
    public List<HashMap<String, Object>> queryAll(@RequestParam("page") Integer pageNow, @RequestParam("rows") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        return goodsService.findAll();
    }
    @RequestMapping("/getXls")
    public void getXls(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String mimeType = request.getSession(true).getServletContext().getMimeType(".xls");
        response.setContentType(mimeType);
        response.setHeader("content-disposition", "attachment;fileName="+URLEncoder.encode("商品表.xls", "UTF-8"));
        Workbook goodsXls = POIUtil.getGoodsXls("商品表", goodsService.findAll());
        POIUtil.download(goodsXls, response.getOutputStream());
    }
    @ResponseBody
    @RequestMapping("/queryById")
    public Map<String, Object> queryById(String id){
        return goodsService.findById(id);
    }
    @RequestMapping("/add")
    public void add(Goods goods, String[] category){
        goodsService.add(goods,category);
    }
    @RequestMapping("/update")
    public void update(Goods goods, String[] category){
        goodsService.update(goods, category);
    }
    @RequestMapping("/delete")
    public void delete(String id){
        goodsService.delete(id);
    }
    @RequestMapping("/deleteGoods")
    public void deleteGoods(String[] ids){
        for (String id : ids) {
            goodsService.delete(id);
        }
    }
}
