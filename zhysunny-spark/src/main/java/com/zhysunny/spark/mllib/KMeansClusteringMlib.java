package com.zhysunny.spark.mllib;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

/**
 * mllib kmeans聚类
 * @author 章云
 * @date 2019/11/5 9:47
 */
public class KMeansClusteringMlib {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[4]").setAppName("KMeansClustering");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        String inputFile = "zhysunny-spark/src/main/resources/spark/kmeans_data.txt";
        JavaRDD<String> rdd = sparkContext.textFile(inputFile);
        JavaRDD<Vector> parseData = rdd.map(s -> {
            String[] array = s.split(" ");
            double[] values = new double[array.length];
            for (int i = 0; i < array.length; i++) {
                values[i] = Double.parseDouble(array[i]);
            }
            return Vectors.dense(values);
        });
        parseData.cache();
        // 使用K均值把数据聚为两类
        int numCluster = 2;
        // 迭代次数
        int iterations = 10;
        KMeansModel km = KMeans.train(parseData.rdd(), numCluster, iterations);
        // 评估，sse表示每个特征到对应聚类中心点距离的平方和
        double sse = km.computeCost(parseData.rdd());
        System.out.println(sse);
        sparkContext.close();
    }

}
