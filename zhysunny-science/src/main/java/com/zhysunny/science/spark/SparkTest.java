package com.zhysunny.science.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;
import java.util.Arrays;
import java.util.Map;

/**
 * spark处理文本挖掘
 * 注意selenium-server-standalone、tika-app有冲突
 * @author 章云
 * @date 2019/11/4 19:01
 */
public class SparkTest {

    public static void main(String[] args) {
        String inputFile = "zhysunny-science/src/main/resources/spark/shakespeare.txt";
        SparkConf conf = new SparkConf().setMaster("local[4]").setAppName("My app");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        JavaRDD<String> rdd = sparkContext.textFile(inputFile).cache();
        long emptyLines = rdd.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String s) throws Exception {
                return s.length() == 0;
            }
        }).count();
        System.out.println("Empty Lines: " + emptyLines);
        JavaPairRDD<String, Integer> wordCounts = rdd.flatMap(s -> Arrays.asList(s.toLowerCase().split(" ")))
        .mapToPair(word -> new Tuple2<>(word, 1)).reduceByKey((a, b) -> a + b);
        Map<String, Integer> wordMap = wordCounts.collectAsMap();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            System.out.println("Word = " + entry.getKey() + ", Frequency = " + entry.getValue());
        }
        sparkContext.close();
    }

}
