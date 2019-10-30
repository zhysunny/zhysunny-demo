package com.zhysunny.science.weka;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 * K均值模型
 * @author 章云
 * @date 2019/10/30 15:56
 */
public class WekaClusterTest {

    public static void main(String[] args) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("zhysunny-science/src/main/resources/arff/cpu.arff");
        Instances cpu = source.getDataSet();
        SimpleKMeans kmeans = new SimpleKMeans();
        // 种子值，用来产生随机数
        kmeans.setSeed(10);
        // 保持数据实例的顺序
        kmeans.setPreserveInstancesOrder(true);
        // 分类个数
        kmeans.setNumClusters(10);
        kmeans.buildClusterer(cpu);
        int[] assignments = kmeans.getAssignments();
        int i = 0;
        for (int clusterNum : assignments) {
            System.out.printf("Instance %d -> Cluster %d\n", i, clusterNum);
            i++;
        }
    }

}
