package com.baizhi.Controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.NoUtil;
import com.baizhi.util.POIUtil;
import com.github.pagehelper.PageHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/login")
    public String login(String username, String password, String captcha, HttpServletRequest request, HttpServletResponse response){
        Jedis jedis = new Jedis("192.168.37.128", 6379);
        String kaptcha = jedis.get("kaptcha");
        if(!kaptcha.equalsIgnoreCase(captcha)){
            return "验证码错误";
        }
        User user = userService.login(username, password);
        if(user==null){
            return "用户名或密码错误";
        }
        Set<String> sets = jedis.hkeys(user.getId() + ":*");
        for (String set : sets) {
            jedis.hset(set, "login","false");
        }
        jedis.hset(user.getId()+":"+NoUtil.getMAC(),"login","true");
        return "登录成功~~~";
    }
    @RequestMapping("/getKaptcha")
    public void getKaptcha(String phone){
        //String sms = SMS("account=xiaochen2018&password=cx521600&mobile=18236572178&content=您的验证码是：【"+NoUtil.getRandom6()+"】。如需帮助请联系客服。", "http://sms.106jiekou.com/utf8/sms.aspx");
        Jedis jedis = new Jedis("192.168.37.128", 6379);
    }
    @RequestMapping("/getXls")
    public void getXls(HttpServletResponse response)throws Exception{
        Workbook goodsXls = POIUtil.getUserXls("用户表", userService.findAll());
        POIUtil.download(goodsXls, response.getOutputStream());
    }
    @RequestMapping("/loginOut")
    public void loginOut(String id){
        Jedis jedis = new Jedis("192.168.37.128", 6379);
        jedis.hset(id+":"+NoUtil.getMAC(),"login","false");
    }
    @RequestMapping("/insist")
    public String insist(){
        Jedis jedis = new Jedis("192.168.37.128", 6379);
        return jedis.hget("user",NoUtil.getMAC());
    }
    @RequestMapping("/queryAllByPage")
    public List<User> queryAllByPage(@RequestParam("page") Integer pageNow, @RequestParam("rows") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        return userService.findAll();
    }
    @RequestMapping("/queryByUser")
    public List<User> queryByUser(String key, String keywords, @RequestParam("page") Integer pageNow, @RequestParam("rows") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        return null;
    }
    @RequestMapping("/queryById")
    public User queryById(String id){
        return userService.findById(id);
    }
    @RequestMapping("/add")
    public void add(User user){
        userService.add(user);
    }
    @RequestMapping("/update")
    public void update(User user){
        userService.update(user);
    }
    @RequestMapping("/delete")
    public void delete(String id){
        userService.delete(id);
    }
    @RequestMapping("/deleteUsers")
    public void deleteUsers(String[] id){
        for (String s : id) {
            userService.delete(s);
        }
    }
}
