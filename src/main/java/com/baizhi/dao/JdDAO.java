package com.baizhi.dao;

import com.baizhi.entity.Product;
import org.springframework.ui.Model;

import java.util.List;

public interface JdDAO {
    List<Product> selectAll(String queryString, String catalog_name, String sort, String price);
}
