package com.baizhi.Controller;

import com.baizhi.entity.Theme;
import com.baizhi.service.ThemeService;
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
@RequestMapping("/theme")
@ResponseBody
public class ThemeController {
    @Resource
    private ThemeService themeService;
    @RequestMapping("/queryAll")
    public List<Theme> queryAll(){
        Theme theme = new Theme();
        theme.setName("--无--");
        List<Theme> list = themeService.findAll();
        list.add(0,theme);
        return list;
    }
    @RequestMapping("/queryAllByPage")
    public List<Theme> queryAllByPage(@RequestParam("page") Integer pageNow, @RequestParam("rows") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        List<Theme> list = themeService.findAll();
        return list;
    }
    @RequestMapping("/queryById")
    public Theme queryById(String id){
        return themeService.findById(id);
    }
    @RequestMapping("/add")
    public void add(Theme theme){
        themeService.add(theme);
    }
    @RequestMapping("/update")
    public void update(Theme theme){
        themeService.update(theme);
    }
    @RequestMapping("/delete")
    public void delete(String id){
        themeService.delete(id);
    }
    @RequestMapping("/deleteThemes")
    public void deleteThemes(String[] ids){
        for (String id : ids) {
            themeService.delete(id);
        }
    }
    @RequestMapping("/img")
    public String img(MultipartFile img, HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/back/theme/img");
        try {
            img.transferTo(new File(realPath, img.getOriginalFilename()));
        }catch (Exception e){
            e.printStackTrace();
        }
        String path = realPath.replace('\\', '/').split("webapp")[1];
        return "/kf"+path+"/"+img.getOriginalFilename();
    }
}
