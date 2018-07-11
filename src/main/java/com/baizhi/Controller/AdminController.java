package com.baizhi.Controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.VerifyCodeUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @RequestMapping("/login")
    public String login(String username, String password, String captcha, HttpServletRequest request){
        /*Jedis jedis = new Jedis("192.168.37.128", 6379);
        String kaptcha = jedis.get("kaptcha");*/
        HttpSession session = request.getSession(true);
        if(!captcha.equalsIgnoreCase((String) session.getAttribute("kaptcha"))){
            return "验证码错误";
        }
        Admin admin = adminService.login(username, password);
        if(admin==null){
            return "用户名或密码错误";
        }
        /*jedis.set("admin",JSONObject.toJSONString(admin));*/
        session.setAttribute("admin",admin);
        return "登录成功~~~";
    }
    @RequestMapping("/menu")
    public String getMenu(){

        return null;
    }
    @ResponseBody
    @RequestMapping("/getAdmin")
    public String getAdmin(HttpServletRequest request){
        /*Jedis jedis = new Jedis("192.168.37.128", 6379);
        return jedis.get("admin");*/
        HttpSession session = request.getSession(true);
        Admin admin = (Admin) session.getAttribute("admin");
        return JSONObject.toJSONString(admin);
    }
    @RequestMapping("/imageCode")
    public void imageCode(HttpServletRequest request, HttpServletResponse response){
        //取得4位验证码
        String kaptcha = VerifyCodeUtil.generateVerifyCode(4);
        System.out.println(kaptcha);
        //获取响应并设置响应类型
        response.setContentType("image/png");
        //将验证码存入redis
        /*Jedis jedis = new Jedis("192.168.37.128", 6379);
        jedis.set("kaptcha", kaptcha);*/
        HttpSession session = request.getSession(true);
        session.setAttribute("kaptcha", kaptcha);
        //将验证码转化为图片
        BufferedImage image = VerifyCodeUtil.getImage(150, 45, kaptcha);
        try {
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @ResponseBody
    @RequestMapping("/queryAllByPage")
    public List<Admin> queryAllByPage(@RequestParam("page") Integer pageNow, @RequestParam("rows") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        return adminService.findAll();
    }
    @ResponseBody
    @RequestMapping("/queryById")
    public Admin queryById(String id){
        return adminService.findById(id);
    }
    @RequestMapping("/add")
    public void add(Admin admin){
        adminService.add(admin);
    }
    @RequestMapping("/update")
    public void update(Admin admin){
        adminService.update(admin);
    }
    @RequestMapping("/delete")
    public void delete(String id){
        adminService.delete(id);
    }
}
