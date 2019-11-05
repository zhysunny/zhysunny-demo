package com.zhysunny.spark.mllib;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.RandomForest;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import org.apache.spark.mllib.util.MLUtils;
import scala.Tuple2;
import java.util.HashMap;

/**
 * mllib 随机森林
 * @author 章云
 * @date 2019/11/5 9:47
 */
public class RandomForestMlib {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[4]").setAppName("KMeansClustering");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        String inputFile = "zhysunny-spark/src/main/resources/spark/sample_binary_classification_data.txt";
        JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(sparkContext.sc(), inputFile).toJavaRDD();
        // 70%数据训练，30%数据测试
        JavaRDD<LabeledPoint>[] dataSplits = data.randomSplit(new double[]{ 0.7, 0.3 });
        JavaRDD<LabeledPoint> trainData = dataSplits[0];
        JavaRDD<LabeledPoint> testData = dataSplits[1];
        // 训练
        Integer classes = 2;
        HashMap<Integer, Integer> nominalFeatures = new HashMap<>();
        Integer trees = 3;
        String featureSubsetProcess = "auto";
        String impurity = "gini";
        Integer maxDepth = 3;
        Integer maxBins = 20;
        Integer seed = 12345;
        RandomForestModel rf = RandomForest
        .trainClassifier(trainData, classes, nominalFeatures, trees, featureSubsetProcess, impurity, maxDepth, maxBins, seed);
        // 评估并计算误差
        JavaPairRDD<Double, Double> label = testData
        .mapToPair(labeledPoint -> new Tuple2<>(rf.predict(labeledPoint.features()), labeledPoint.label()));
        Double error = 1.0 * label.filter(tuple -> !tuple._1().equals(tuple._2())).count() / testData.count();
        System.out.println("Test Error: " + error);
        System.out.println(rf.toDebugString());
        sparkContext.close();
    }

}
