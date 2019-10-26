package com.zhysunny.science.data.get;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * 获取数据需要掌握内容
 * 1.使用commons-io常用的文件操作
 * 2.使用tika提取常见文本文件内容(pdf、word、html等非结构化)
 * 3.解析常见的半结构化文件(csv、excel、xml、json)
 * 4.使用JSoup、Selenium Webdriver提取网站数据(爬虫)
 * 5.提取mysql、oracle等数据库数据
 * @author 章云
 * @date 2019/9/28 18:57
 */
public class GetData {

    public static void main(String[] args) {
        File path = new File(new File("").getAbsolutePath());
        File textFile = new File(path, "pom.xml");
        URL pdfUrl = GetData.class.getClassLoader().getResource("pdf/JSON.pdf");
        String url = "https://www.baidu.com/";
        getDataForJSoup(url);
    }

    /**
     * commons-io递归扫描所有文件
     * @param path 目录
     */
    public static void listFiles(File path) {
        System.out.println(path);
        // 目录，文件过滤器，目录过滤器
        Collection<File> files = FileUtils.listFiles(path, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
        System.out.println(files.size());
    }

    /**
     * 按行读取文件内容，比BufferedReader简洁
     * @param file 文本文件
     */
    public static void readLines(File file) {
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
        }
    }

    /**
     * 直接读取整个文件的内容
     * @param file 文本文件
     */
    public static void readFileToString(File file) {
        try {
            System.out.println(FileUtils.readFileToString(file, "UTF-8"));
        } catch (IOException e) {
        }
    }

    /**
     * tika解析pdf文件，注意不能是加密文件，不能是扫描文件
     * @param url
     */
    public static void readPdf(URL url) {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(-1);
        try {
            parser.parse(url.openStream(), handler, new Metadata(), new ParseContext());
            System.out.println(handler.toString());
        } catch (IOException e) {
        } catch (SAXException e) {
        } catch (TikaException e) {
        }
    }

    /**
     * 获取网页数据
     * @param url
     */
    public static void getDataForJSoup(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(10000).ignoreHttpErrors(true).get();
        } catch (IOException e) {
        }
        System.out.println(doc.title());
        System.out.println(doc.body().text());
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            System.out.println(link.attr("href"));
            System.out.println(link.text());
            System.out.println(link.outerHtml());
            System.out.println(link.html());
        }
    }

    /**
     * 获取网页数据(存在版本冲突和兼容性问题，未解决)
     * @param url
     */
    public static void getDataForSelenium(String url) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"su\"]"));
        System.out.println(webElement.getText());
    }

}
