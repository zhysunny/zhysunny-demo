package com.zhysunny.science.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 创建或更新索引
 * @author 章云
 * @date 2018年7月21日 下午10:08:21
 */
public class CreateIndex {
    public static void main(String[] args) throws IOException {
        String path = "zhysunny-test-project/zhysunny-science/src/main/resources/lucene";
        Directory directory = FSDirectory.open(Paths.get(path));// 设置索引库存放的位置
        Analyzer analyzer = new StandardAnalyzer();// 官方推荐
        IndexWriterConfig config = new IndexWriterConfig(analyzer);// 指定分析器，对文档内容进行分析
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);// 设置添加或更新数据
        IndexWriter indexWriter = new IndexWriter(directory, config);
        indexDocs(indexWriter, Paths.get("zhysunny-test-project/zhysunny-science/src/main/resources/pdf/JSON.pdf"));
        indexWriter.close();
    }

    public static void indexDocs(IndexWriter writer, Path path) throws IOException {
        if (Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    indexDocs(writer, file, attrs.lastModifiedTime().toMillis());
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            indexDocs(writer, path, Files.getLastModifiedTime(path).toMillis());
        }
    }

    public static void indexDocs(IndexWriter writer, Path file, long lastModified) {
        try (InputStream stream = Files.newInputStream(file)) {
            Document doc = new Document();
            doc.add(new StringField("path", file.toString(), Store.YES));
            doc.add(new LongPoint("modified", lastModified));
            doc.add(new TextField("content", file.toString(), Store.YES));
            doc.add(new StringField("path", readPdf(file), Store.YES));
            if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
                System.out.println("add " + file);
                writer.addDocument(doc);
            } else {
                System.out.println("update " + file);
                writer.updateDocument(new Term("path", file.toString()), doc);
            }
        } catch (IOException e) {
        }
    }

    public static String readPdf(Path file) {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(-1);
        try {
            parser.parse(Files.newInputStream(file), handler, new Metadata(), new ParseContext());
            return handler.toString();
        } catch (IOException e) {
        } catch (SAXException e) {
        } catch (TikaException e) {
        }
        return null;
    }

}
