package com.baizhi.service;

import com.baizhi.dao.JdDAO;
import com.baizhi.dao.JdDAOImpl;
import com.baizhi.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JdServiceImpl implements  JdService  {


    @Override
    public List<Product> findAll(String queryString, String catalog_name, String price, String sort) {
        JdDAO jdDAO = new JdDAOImpl();
        return jdDAO.selectAll(queryString,catalog_name,price,sort);
    }
}
