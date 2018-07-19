package com.baizhi.Controller;

import com.baizhi.entity.Product;
import com.baizhi.service.JdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/jd")
public class JDController {
    @Resource
    private JdService jdService;
    @RequestMapping("/queryAll")
    public String queryAll(String queryString, String catalog_name, String sort, String price, Model model){
        List<Product> products = jdService.findAll(queryString, catalog_name, price, sort);

        model.addAttribute("products",products);
        model.addAttribute("queryString",queryString);
        model.addAttribute("catalog_name",catalog_name);
        model.addAttribute("price",price);
        model.addAttribute("sort",sort);
        return "product_list";
    }
}
