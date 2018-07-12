package com.baizhi.test;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLucene {
    @Test
    public void getDecoment() throws Exception {
        //创建索引目录
        FSDirectory dir = FSDirectory.open(new File("./index"));
        //创建索引的配置对象
        //1.引入Lucene版本
        StandardAnalyzer standardAnalyzer = new StandardAnalyzer(Version.LUCENE_44);
        //2.创建分词器
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, standardAnalyzer);
        //创建索引的写出对象
        //3.索引写出位置与配置对象
        IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);
        //对一篇文章创建索引
        //创建document文档
    }
}
