package com.zhysunny.science.data.statistic;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import java.util.*;

/**
 * 概要统计（SummaryStatistics不把数据存内存中，所以有些值取不到，像中位数、峰度）
 * @author 章云
 * @date 2019/9/29 22:38
 */
public class Summary {
    public static void main(String[] args) {
        summary(DataSet.LIST);
    }

    public static void summary(List<Double> list) {
        Collections.sort(list);
        System.out.println(list);
        // 线程不安全
        SummaryStatistics stats = new SummaryStatistics();
        // 线程安全
        // stats = new SynchronizedSummaryStatistics();
        list.forEach(v -> stats.addValue(v));
        System.out.println("getMax：" + stats.getMax());
        System.out.println("getMin：" + stats.getMin());
        System.out.println("getMean：" + stats.getMean());
        System.out.println("getSum：" + stats.getSum());
        // 标准差
        System.out.println("getStandardDeviation：" + stats.getStandardDeviation());
        // 方差
        System.out.println("getVariance：" + stats.getVariance());
        // 几何平均数
        System.out.println("getGeometricMean：" + stats.getGeometricMean());
        // count
        System.out.println("getN：" + stats.getN());
        // 平方和
        System.out.println("getSumsq：" + stats.getSumsq());
        // 二次平均数
        System.out.println("getQuadraticMean：" + stats.getQuadraticMean());
        System.out.println("getPopulationVariance：" + stats.getPopulationVariance());
    }
}
