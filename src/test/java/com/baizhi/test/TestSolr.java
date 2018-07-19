package com.baizhi.test;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSolr {
    @Resource
    private SolrClient solrClient;
    @Test
    public void test1() throws Exception{
        SolrQuery params = new SolrQuery("精致");
        params.set("df","product_keywords")
                .set("fq","product_catalog_name:幽默杂货");
        QueryResponse query = solrClient.query(params);
        SolrDocumentList results = query.getResults();
        System.out.println("总条数：" + results.getNumFound());
        for (SolrDocument result : results) {
            System.out.println("id: "+result.get("id"));
            System.out.println("name: "+result.get("product_name"));
            System.out.println("catalog_name: "+result.get("product_catalog_name"));
            System.out.println("description: "+result.get("product_description"));
        }
    }

}
