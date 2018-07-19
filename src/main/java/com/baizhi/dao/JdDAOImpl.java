package com.baizhi.dao;

import com.baizhi.entity.Product;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class JdDAOImpl implements JdDAO {

    @Override
    public List<Product> selectAll(String queryString, String catalog_name, String sort, String price) {

        List<Product> list = new ArrayList<Product>();
        try{
            SolrClient solrClient = new HttpSolrClient("http://localhost:9696/solr/collection1");
        SolrQuery params = new SolrQuery();
        params.setQuery(queryString)
                .set("df","product_keywords");
        if(!StringUtils.isEmpty(catalog_name)){
            System.out.println("catalog_name: "+catalog_name);
            params.addFilterQuery("product_catalog_name:"+catalog_name);
        }
        if(!StringUtils.isEmpty(price)){
            String start = price.split("-")[0];
            String end = price.split("-")[1];
            System.out.println("product_price:["+ start +" TO "+ end +"]");
            params.addFilterQuery("product_price:["+ start +" TO "+ end +"]");
        }
        //是否设置排序
        if(!StringUtils.isEmpty(sort)){
            if("1".equals(sort)){
                params.setSort("product_price",SolrQuery.ORDER.desc);
            }else {
                params.setSort("product_price",SolrQuery.ORDER.asc);
            }
        }
            //开启高亮
            params.setHighlight(true)
                    .setHighlightSimplePre("<span style='color:red;'>")
                    .setHighlightSimplePost("</span>")
                    .addHighlightField("product_name");

            QueryResponse queryResponse = solrClient.query(params);
        //获取高亮结果
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

        SolrDocumentList results = queryResponse.getResults();
        System.out.println("总条数: "+results.getNumFound());
        for (SolrDocument result : results) {
            Product product = new Product();
            product.setPid(result.get("id").toString());
            product.setCatalog_name(result.get("product_catalog_name").toString());
            System.out.println(result);
            System.out.println(result.get("product_picture"));
            product.setPicture(result.get("product_picture").toString());
            Map<String, List<String>> stringListMap = highlighting.get(result.get("id"));
            if(stringListMap.containsKey("product_name")){
                product.setName(stringListMap.get("product_name").get(0));
            }else{
                product.setName(result.get("product_name").toString());
            }
            product.setPrice(Float.valueOf(result.get("product_price").toString()));
            list.add(product);
        }
    } catch (SolrServerException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        return list;
    }
}
