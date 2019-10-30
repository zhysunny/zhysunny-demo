package com.zhysunny.science.javaml;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.classification.evaluation.CrossValidation;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.distance.PearsonCorrelationCoefficient;
import net.sf.javaml.featureselection.ranking.RecursiveFeatureEliminationSVM;
import net.sf.javaml.featureselection.scoring.GainRatio;
import net.sf.javaml.featureselection.subset.GreedyForwardSelection;
import net.sf.javaml.tools.data.FileHandler;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * java机器学习
 * @author 章云
 * @date 2019/10/30 21:39
 */
public class JavaMachineLearning {

    public static void main(String[] args) throws IOException {
        Dataset data = FileHandler.loadDataset(new File("zhysunny-science/src/main/resources/data/iris.data"), 4, ",");
        System.out.println(data);
        // 数据输出
        FileHandler.exportDataset(data, new File("zhysunny-science/src/main/resources/javaml/iris.txt"));
        // 聚类
        Clusterer km = new KMeans();
        Dataset[] clusters = km.cluster(data);
        for (Dataset cluster : clusters) {
            System.out.println("Cluster: " + cluster);
        }
        // 聚类的误差平方和
        ClusterEvaluation sse = new SumOfSquaredErrors();
        double score = sse.score(clusters);
        System.out.println(score);
        // 分类
        Classifier knn = new KNearestNeighbors(5);
        knn.buildClassifier(data);
        // 交叉验证
        CrossValidation cv = new CrossValidation(knn);
        Map<Object, PerformanceMeasure> cvEval = cv.crossValidation(data);
        // {Iris-versicolor=[TP=47.0, FP=2.0, TN=98.0, FN=3.0], Iris-virginica=[TP=48.0, FP=3.0, TN=97.0, FN=2.0], Iris-setosa=[TP=50.0, FP=0.0, TN=100.0, FN=0.0]}
        // 真正、假正、真负、假负
        System.out.println(cvEval);
        // Held-out测试
        Dataset testData = FileHandler.loadDataset(new File("zhysunny-science/src/main/resources/data/iris.data"), 4, ",");
        Map<Object, PerformanceMeasure> testEval = EvaluateDataset.testDataset(knn, testData);
        for (Map.Entry<Object, PerformanceMeasure> entry : testEval.entrySet()) {
            Object classVariable = entry.getKey();
            PerformanceMeasure performanceMeasure = entry.getValue();
            System.out.println(classVariable + " class has " + performanceMeasure.getAccuracy());
        }
        // 特征打分
        GainRatio gainRatio = new GainRatio();
        gainRatio.build(data);
        for (int i = 0; i < gainRatio.noAttributes(); i++) {
            System.out.println(gainRatio.score(i));
        }
        // 特征排序
        RecursiveFeatureEliminationSVM featureRank = new RecursiveFeatureEliminationSVM(0.2);
        featureRank.build(data);
        for (int i = 0; i < featureRank.noAttributes(); i++) {
            System.out.println(featureRank.rank(i));
        }
        // 特征子集选择
        GreedyForwardSelection featureSelection = new GreedyForwardSelection(5, new PearsonCorrelationCoefficient());
        featureSelection.build(data);
        System.out.println(featureSelection.selectedAttributes());
    }

}
