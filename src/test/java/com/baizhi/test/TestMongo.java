package com.baizhi.test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMongo {
    @Resource
    private MongoTemplate mongoTemplate;
    private MongoClient mongoClient;

    /*@Before
    public void begin(){
        mongoClient = new MongoClient("192.168.37.128",27017);
    }
*/
    /**
     * test1 添加数据
     */
    @Test
    public void testTemo(){
        System.out.println(mongoTemplate);
    }
    @Test
    public void test1(){
        String id = UUID.randomUUID().toString();
        System.out.println(id);
        MongoDatabase ems = mongoClient.getDatabase("ems");
        MongoCollection<Document> t_user = ems.getCollection("t_user");

    }

    public void test2(){
        Query query = new Query();

    }

}
