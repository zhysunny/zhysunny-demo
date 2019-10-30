package com.zhysunny.science.weka;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 * 有标签数据进行聚类
 * @author 章云
 * @date 2019/10/27 21:02
 */
public class WekaClassesToClusterTest {

    public static void main(String[] args) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(
        "zhysunny-science/src/main/resources/arff/weather.nominal.arff");
        Instances weather = source.getDataSet();
        weather.setClassIndex(weather.numAttributes() - 1);
        Remove filter = new Remove();
        filter.setAttributeIndices("" + (weather.classIndex() + 1));
        filter.setInputFormat(weather);
        Instances dataCluster = Filter.useFilter(weather,filter);
        EM cluster = new EM();
        cluster.buildClusterer(dataCluster);
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(cluster);
        eval.evaluateClusterer(weather);
        System.out.println(eval.clusterResultsToString());
    }

}
