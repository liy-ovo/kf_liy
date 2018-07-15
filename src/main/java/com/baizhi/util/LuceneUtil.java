package com.baizhi.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryTermScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 *  Lucene工具类
 */
public class LuceneUtil {
    private static FSDirectory dir;
    private static IndexWriterConfig conf;
    private static DirectoryReader reader;
    private static Version version;
    public static Analyzer analyzer;
    private static final ThreadLocal<IndexWriter> t = new ThreadLocal<IndexWriter>();
    static {
        try {
            version = Version.LUCENE_44;
            //创建索引目录
            dir = FSDirectory.open(new File("./index"));
            //创建索引配置对象
            analyzer = new SmartChineseAnalyzer(version);
            conf = new IndexWriterConfig(version,analyzer);
            LogDocMergePolicy logDocMergePolicy = new LogDocMergePolicy();
            logDocMergePolicy.setMergeFactor(2);
            conf.setMergePolicy(logDocMergePolicy);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 获取indexWriter
     */
    public static IndexWriter getIndexWriter(){
        IndexWriter indexWriter = t.get();
        if(indexWriter==null){
            try {
                indexWriter = new IndexWriter(dir, conf);
                t.set(indexWriter);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return indexWriter;
    }
    /**
     * 获取IndexSearcher
     */
    public static IndexSearcher getIndexReader() {
        try{
            //读取索引位置
            reader = DirectoryReader.open(dir);
        }catch (Exception e){

        }
        return new IndexSearcher(reader);
    }
    /**
     * 获取数字范围过滤器
     */
    public static Filter getFilter(Float min, Float max){
        Filter filter = NumericRangeFilter.newFloatRange("price", min, max, true, false);
        return filter;
    }
    /**
     * 获取高亮器
     */
    public static Highlighter getHighLighter(Query query){
        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");
        QueryTermScorer scorer = new QueryTermScorer(query);
        Highlighter highlighter = new Highlighter(formatter, scorer);
        return highlighter;
    }
    /**
     * 获取多字段查询条件Query
     */
    public static Query getMultiFieldQueryParser(String[] fields, String keyword){
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(version, fields, analyzer);
        Query query = null;
        try {
            query = queryParser.parse(keyword);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 提交事务
     */
    public static void commit(){
        IndexWriter indexWriter = getIndexWriter();
        try{
            indexWriter.commit();
            indexWriter.close();
            t.remove();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 回滚事务
     */
    public static void rollback(){
        try{
            IndexWriter indexWriter = getIndexWriter();
            indexWriter.rollback();
            indexWriter.close();
            t.remove();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
