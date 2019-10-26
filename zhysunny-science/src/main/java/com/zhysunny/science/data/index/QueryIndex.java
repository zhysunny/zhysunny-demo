package com.zhysunny.science.data.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * 搜索
 * @author 章云
 * @date 2019/9/28 21:06
 */
public class QueryIndex {
    public static void main(String[] args) throws IOException, ParseException {
        String path = "zhysunny-test-project/zhysunny-science/src/main/resources/lucene";
        Directory directory = FSDirectory.open(Paths.get(path));// 设置索引库存放的位置
        // 1.创建IndexReader打开索引库
        IndexReader indexReader = DirectoryReader.open(directory);
        // 2.创建搜索对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // 3.创建分词器
        Analyzer analyzer = new StandardAnalyzer();// 官方推荐
        QueryParser queryParser = new QueryParser("content", analyzer);
        // 3.创建查询对象
        Query query = queryParser.parse("json");
        // 4.开始查询
        TopDocs topDocs = indexSearcher.search(query, 5);
        // 5.遍历结果
        int count = topDocs.totalHits;// 匹配的总记录数
        System.out.println(count);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        Document document = null;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            float score = scoreDoc.score;// 匹配分数，默认降序排序
            document = indexSearcher.doc(doc);
            // System.out.println("ID:" + doc);
            System.out.println("score:" + score);
            //			System.out.println("fileName:" + document.get("fileName"));
            //			System.out.println("fileSize:" + document.get("fileSize"));
            //			System.out.println("filePath:" + document.get("filePath"));
            System.out.println("content:" + document.get("content"));
            System.out.println("------------------------------------");
        }
        // 6.关闭资源
        indexReader.close();
    }
}
