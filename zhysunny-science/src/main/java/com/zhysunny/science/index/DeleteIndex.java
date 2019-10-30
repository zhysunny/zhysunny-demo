package com.zhysunny.science.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * 删除索引
 * @author 章云
 * @date 2019/9/28 21:06
 */
public class DeleteIndex {
    public static void main(String[] args) throws IOException {
        String path = "zhysunny-test-project/zhysunny-science/src/main/resources/lucene";
        Directory directory = FSDirectory.open(Paths.get(path));// 设置索引库存放的位置
        Analyzer analyzer = new StandardAnalyzer();// 官方推荐
        IndexWriterConfig config = new IndexWriterConfig(analyzer);// 指定分析器，对文档内容进行分析
        IndexWriter indexWriter = new IndexWriter(directory, config);
        // indexWriter.deleteAll();//删除所有索引
        Query query = new TermQuery(new Term("content", "java"));
        indexWriter.deleteDocuments(query);// 按条件删除索引
        indexWriter.close();
    }
}
