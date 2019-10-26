package com.zhysunny.science.data.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * 更新索引
 * @author 章云
 * @date 2019/9/28 21:06
 */
public class UpdateIndex {
    public static void main(String[] args) throws IOException {
        String path = "zhysunny-test-project/zhysunny-science/src/main/resources/lucene";
        Directory directory = FSDirectory.open(Paths.get(path));// 设置索引库存放的位置
        Analyzer analyzer = new StandardAnalyzer();// 官方推荐
        IndexWriterConfig config = new IndexWriterConfig(analyzer);// 指定分析器，对文档内容进行分析
        IndexWriter indexWriter = new IndexWriter(directory, config);
        // 创建一个Document对象
        Document document = new Document();
        // 向document对象中添加域。
        // 不同的document可以有不同的域，同一个document可以有相同的域。
        document.add(new TextField("filename", "要更新的文档", Store.YES));
        document.add(
        new TextField("content", "2013年11月18日 - Lucene 简介 Lucene 是一个基于 Java 的全文信息检索工具包,它不是一个完整的搜索应用程序,而是为你的应用程序提供索引和搜索功能。", Store.YES));
        indexWriter.updateDocument(new Term("content", "java"), document);
        // 关闭indexWriter
        indexWriter.close();
    }
}
