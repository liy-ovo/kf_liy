package com.baizhi.mongo;

import com.baizhi.entity.Tuser;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("tuserDAO")
public class TuserDAOImpl{
    @Resource
    private MongoTemplate mongoTemplate;

    public List<Tuser> selectAll() {
        List<Tuser> list = mongoTemplate.findAll(Tuser.class,"t_user");
        return list;
    }


    public Tuser selectById(String id) {
        Tuser tuser = mongoTemplate.findById(id, Tuser.class,"t_user");
        return tuser;
    }


    public void add(Tuser tuser) {
        mongoTemplate.save(tuser,"t_user");
    }


    public void update(Tuser tuser) {
        System.out.println(tuser);
        mongoTemplate.save(tuser,"t_user");
    }


    public void delete(String id) {
        System.out.println(id);
        Tuser tuser = new Tuser();
        tuser.setId(id);
        mongoTemplate.remove(tuser,"t_user");
    }
}
