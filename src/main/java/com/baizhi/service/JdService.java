package com.baizhi.service;

import com.baizhi.entity.Product;

import java.util.List;

public interface JdService {



        public List<Product> findAll(String queryString, String catalog_name, String price, String sort);


}
