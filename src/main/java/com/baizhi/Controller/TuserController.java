package com.baizhi.Controller;

import com.baizhi.entity.Tuser;
import com.baizhi.service.TuserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tuser")
public class TuserController {
    @Resource
    private TuserService tuserService;
    @RequestMapping("/queryAll")
    public List<Tuser> queryAll(){
        return tuserService.findAll();
    }
    @RequestMapping("/queryById")
    public Tuser queryById(String id){
        return tuserService.findById(id);
    }
    @RequestMapping("/add")
    public void add(Tuser tuser){
        tuserService.add(tuser);
    }
    @RequestMapping("/update")
    public void update(Tuser tuser){
        tuserService.update(tuser);
    }
    @RequestMapping("/delete")
    public void delete(String id){
        tuserService.delete(id);
    }
}
