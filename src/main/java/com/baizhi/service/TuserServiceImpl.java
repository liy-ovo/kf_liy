package com.baizhi.service;

import com.baizhi.mongo.TuserDAOImpl;
import com.baizhi.entity.Tuser;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("tuserService")
@ComponentScan("com.baizhi.mongo")
public class TuserServiceImpl implements TuserService{
    @Resource
    private TuserDAOImpl tuserDAO;
    @Override
    public List<Tuser> findAll() {
        return tuserDAO.selectAll();
    }

    @Override
    public Tuser findById(String id) {
        return tuserDAO.selectById(id);
    }

    @Override
    public void add(Tuser tuser) {
        tuser.setId(UUID.randomUUID().toString());
        tuserDAO.add(tuser);
    }

    @Override
    public void update(Tuser tuser) {
        tuserDAO.update(tuser);
    }

    @Override
    public void delete(String id) {
        tuserDAO.delete(id);
    }
}
