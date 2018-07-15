package com.baizhi.test;

import com.baizhi.entity.Goods;
import com.baizhi.entity.Shop;
import com.baizhi.entity.Theme;
import com.baizhi.entity.User;
import com.baizhi.service.GoodsService;
import com.baizhi.service.ShopService;
import com.baizhi.service.ThemeService;
import com.baizhi.service.UserService;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLucene {
    @Resource
    private GoodsService goodsService;
    @Resource
    private ShopService shopService;
    @Resource
    private ThemeService themeService;
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
        List<HashMap<String, Object>> all = goodsService.findAll();
        for (HashMap<String, Object> stringObjectHashMap : all) {
            Document document = new Document();
            Goods goods = (Goods)stringObjectHashMap.get("goods");
            document.add(new StringField("id", goods.getId(),Field.Store.YES));
            document.add(new StringField("name", goods.getName(),Field.Store.YES));
            document.add(new StringField("specifications", goods.getSpecifications(),Field.Store.YES));
            document.add(new DoubleField("price", goods.getPrice(),Field.Store.YES));
            document.add(new IntField("sales", goods.getSales(),Field.Store.YES));
            Shop shop = shopService.findById(goods.getSeller());
            String seller;
            if(shop == null){
                seller = "";
            }else {
                seller = shop.getName();
            }
            document.add(new StringField("seller", seller,Field.Store.YES));
            document.add(new StringField("putTime", new SimpleDateFormat("yyyy-MM-dd").format(goods.getPutTime()),Field.Store.YES));
            Theme theme = themeService.findById(goods.getThemeId());
            String themeString;
            if(theme==null){
                themeString = "";
            }else{
                themeString = theme.getName();
            }
            document.add(new StringField("themeId", themeString,Field.Store.YES));
            document.add(new TextField("instructions", goods.getInstructions(),Field.Store.YES));
            indexWriter.addDocument(document);
        }
        indexWriter.commit();
        indexWriter.close();
    }
    @Test
    public void testSelect() throws Exception{
        //创建索引目录
        FSDirectory dir = FSDirectory.open(new File("./index"));
        //读取索引位置
        DirectoryReader reader = DirectoryReader.open(dir);
        //创建索引搜索对象
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        //搜索  搜索条件+搜索条数
        TopDocs topDocs = indexSearcher.search(new TermQuery(new Term("name","容易")),100);
        //相关度排序
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for(int i=0; i<scoreDocs.length; i++){
            //文章在索引库中的唯一标识
            int doc = scoreDocs[i].doc;
            System.out.println("当前文章得分"+scoreDocs[i].score);
            Document document = indexSearcher.doc(doc);
            System.out.println(document.get("id"));
            System.out.println(document.get("name"));
            System.out.println(document.get("instructions"));
            System.out.println("----------------------");
            System.out.println();
        }
    }
    @Test
    public void testUpdate() throws Exception{
        //创建索引目录
        FSDirectory dir = FSDirectory.open(new File("./index"));
        //创建索引配置对象
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_44,new StandardAnalyzer(Version.LUCENE_44));
        //创建索引操作对象
        IndexWriter indexWriter = new IndexWriter(dir, conf);
    }
    @Test
    public void testDelete() throws Exception{
        //创建索引目录
        FSDirectory dir = FSDirectory.open(new File("./index"));
        //创建索引配置对象
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_44, new StandardAnalyzer(Version.LUCENE_44));
        //创建索引操作对象
        IndexWriter indexWriter = new IndexWriter(dir, conf);
        //根据条件删除索引
        indexWriter.deleteDocuments(new Term("id","1927e4fd-0f81-4728-b87b-ddc1b0e540bb"));
        //提交事务
        indexWriter.commit();
        indexWriter.close();
    }
}
