package com.baizhi.dao;

import com.baizhi.entity.Goods;
import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import java.util.ArrayList;
import java.util.List;

public class LuceneDAOImpl implements LuceneDAO {
    @Override
    public List<Goods> getAllGoods(String keyword) throws Exception {
        List<Goods> list = new ArrayList<Goods>();
        IndexSearcher indexReader = LuceneUtil.getIndexReader();
        String[] fields = {"name","instructions"};
        Query query = LuceneUtil.getMultiFieldQueryParser(fields, keyword);
        TopDocs topDocs = indexReader.search(query, 100);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        Highlighter highLighter = LuceneUtil.getHighLighter(query);
        for (ScoreDoc scoreDoc : scoreDocs) {
            Document doc = indexReader.doc(scoreDoc.doc);
            Goods goods = new Goods();
            goods.setId(doc.get("id"));
            String name = highLighter.getBestFragment(LuceneUtil.analyzer, "name", doc.get("name"));
            if(name==null){
                name = doc.get("name");
            }
            goods.setName(name);
            goods.setPrice(Double.parseDouble(doc.get("price")));
            goods.setSales(Integer.parseInt(doc.get("sales")));
            goods.setSpecifications(doc.get("specifications"));
            String instructions = highLighter.getBestFragment(LuceneUtil.analyzer, "instructions", doc.get("instructions"));
            if(instructions==null){
                instructions = doc.get("instructions");
            }
            goods.setInstructions(instructions);
            list.add(goods);
        }
        return list;
    }

    @Override
    public void add(Goods goods){
        try{
        Document document = new Document();
        document.add(new StringField("id", goods.getId(),Field.Store.YES));
        document.add(new TextField("name",goods.getName(),Field.Store.YES));
        document.add(new DoubleField("price",goods.getPrice(),Field.Store.YES));
        document.add(new StringField("specifications",goods.getSpecifications(),Field.Store.NO));
        document.add(new IntField("sales",goods.getSales(),Field.Store.YES));
        document.add(new TextField("instructions",goods.getInstructions(),Field.Store.YES));
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        indexWriter.addDocument(document);
        LuceneUtil.commit();
        }catch (Exception e){
            LuceneUtil.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Goods goods){
        Document document = new Document();
        document.add(new StringField("id", goods.getId(),Field.Store.YES));
        document.add(new TextField("name",goods.getName(),Field.Store.YES));
        document.add(new DoubleField("price",goods.getPrice(),Field.Store.YES));
        document.add(new StringField("specifications",goods.getSpecifications(),Field.Store.NO));
        document.add(new IntField("sales",goods.getSales(),Field.Store.YES));
        document.add(new TextField("instructions",goods.getInstructions(),Field.Store.YES));
        try{
            IndexWriter indexWriter = LuceneUtil.getIndexWriter();
            indexWriter.updateDocument(new Term("id",goods.getId()),document);
            LuceneUtil.commit();
        }catch (Exception e){
            LuceneUtil.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id){
        try{
            IndexWriter indexWriter = LuceneUtil.getIndexWriter();
            indexWriter.deleteDocuments(new Term("id",id));
            LuceneUtil.commit();
        }catch (Exception e){
            LuceneUtil.rollback();
            e.printStackTrace();
        }
    }
}
