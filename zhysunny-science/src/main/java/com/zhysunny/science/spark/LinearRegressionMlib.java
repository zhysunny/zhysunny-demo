package com.zhysunny.science.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.regression.LinearRegressionWithSGD;
import scala.Tuple2;

/**
 * mllib 线性回归
 * @author 章云
 * @date 2019/11/5 9:47
 */
public class LinearRegressionMlib {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[4]").setAppName("KMeansClustering");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        String inputFile = "zhysunny-science/src/main/resources/spark/lpsa.data";
        JavaRDD<String> rdd = sparkContext.textFile(inputFile);
        JavaRDD<LabeledPoint> parseData = rdd.map(s -> {
            String[] array = s.split(",");
            String[] features = array[1].split(" ");
            double[] featureVector = new double[features.length];
            double[] values = new double[array.length];
            for (int i = 0; i < array.length; i++) {
                featureVector[i] = Double.parseDouble(features[i]);
            }
            return new LabeledPoint(Double.parseDouble(array[0]), Vectors.dense(featureVector));
        });
        parseData.cache();
        // 创建模型
        int iterations = 10;
        LinearRegressionModel lrm = LinearRegressionWithSGD.train(JavaRDD.toRDD(parseData), iterations);
        // 评估
        JavaRDD<Tuple2<Double, Double>> predictions = parseData.map(point -> new Tuple2<>(lrm.predict(point.features()), point.label()));
        JavaRDD<Object> map = predictions.map(pair -> Math.pow(pair._1() - pair._2(), 2.0));
        double mse = new JavaDoubleRDD(map.rdd()).mean();
        System.out.println(mse);
        sparkContext.close();
    }

}
