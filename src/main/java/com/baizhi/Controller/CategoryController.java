package com.baizhi.Controller;

import com.baizhi.entity.Category;
import com.baizhi.entity.CategoryExample;
import com.baizhi.service.CategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/category")
@ResponseBody
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @RequestMapping("/queryAll")
    public List<Category> queryAll(){
        List<Category> allCategory = categoryService.findAllCategory();
        return allCategory;
    }
    @RequestMapping("/queryAllByPage")
    public List<Category> queryAllByPage(@RequestParam("page") Integer pageNow, @RequestParam("rows") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        List<Category> allCategory = categoryService.findAllCategory();
        return allCategory;
    }
    @RequestMapping("/queryFirstLevels")
    public List<Category> queryFirstLevels(){
        CategoryExample example = new CategoryExample();
        example.createCriteria().andPidIsNull();
        List<Category> list = categoryService.findByCategory(example);
        Category category = new Category();
        category.setName("---无---");
        category.setId(null);
        list.add(0,category);
        return list;
    }
    @RequestMapping("/querySecondLevels")
    public List<Category> querySecondLevels(){
        CategoryExample example = new CategoryExample();
        example.createCriteria().andPidIsNotNull();
        List<Category> list = categoryService.findByCategory(example);
        return list;
    }
    @RequestMapping("/queryById")
    public Category queryById(@RequestParam("id") String id){
        return categoryService.findById(id);
    }
    @RequestMapping("/add")
    public void add(Category category){
        categoryService.add(category);
    }
    @RequestMapping("/update")
    public void update(Category category){
        System.out.println(category);
        categoryService.update(category);
    }
    @RequestMapping("/delete")
    public void delete(@RequestParam("ids") String[] ids){
        for (String s : ids) {
            categoryService.delete(s);
        }
    }
    @RequestMapping("/img")
    public String img(MultipartFile img, HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/back/category/img");
        try {
            img.transferTo(new File(realPath, img.getOriginalFilename()));
        }catch (Exception e){
            e.printStackTrace();
        }
        String path = realPath.replace('\\', '/').split("webapp")[1];
        return "/kf"+path+"/"+img.getOriginalFilename();
    }
}
