package com.zhysunny.science.data.statistic;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述性统计
 * @author 章云
 * @date 2019/9/29 22:02
 */
public class Description {

    public static void main(String[] args) {
        description(DataSet.LIST);
    }

    public static void description(List<Double> list) {
        Collections.sort(list);
        System.out.println(list);
        // 线程不安全
        DescriptiveStatistics stats = new DescriptiveStatistics();
        // 线程安全
        // stats = new SynchronizedDescriptiveStatistics();
        list.forEach(v -> stats.addValue(v));
        System.out.println("getMax：" + stats.getMax() + " == " + list.get(list.size() - 1));
        System.out.println("getMin：" + stats.getMin() + " == " + list.get(0));
        System.out.println("getN：" + stats.getN() + " == " + list.size());
        System.out.println("getSum：" + stats.getSum() + " == " + list.stream().collect(Collectors.summingDouble(x -> x)));
        System.out.println("getMean：" + stats.getMean() + " == " + list.stream().collect(Collectors.summingDouble(x -> x)) / list.size());
        // 方差
        // x - stats.getMean()的平方和
        Double collect = list.stream().map(x -> x - stats.getMean()).collect(Collectors.summingDouble(x -> Math.pow(x, 2)));
        // x - stats.getMean()和的平方除以n
        Double collect2 = Math.pow(list.stream().map(x -> x - stats.getMean()).collect(Collectors.summingDouble(x -> x)), 2) / list.size();
        System.out.println("getVariance：" + stats.getVariance() + " == " + (collect - collect2) / (list.size() - 1));
        // 标准差
        System.out.println("getStandardDeviation：" + stats.getStandardDeviation() + " == " + Math.pow(stats.getVariance(), 0.5));
        // 方差 （标准的公式）
        System.out.println("getPopulationVariance：" + stats.getPopulationVariance() + " == "
        + list.stream().map(x -> x - stats.getMean()).collect(Collectors.summingDouble(x -> Math.pow(x, 2))) / list.size());
        // 中位数
        System.out.println("getPercentile：" + stats.getPercentile(50));
        // 几何平均数（元素包含0的集合不建议使用）
        System.out.println(
        "getGeometricMean：" + stats.getGeometricMean() + " == " + Math.pow(list.stream().reduce((x, y) -> x * y).get(), 1.0 / list.size()));
        // 平方和
        System.out.println("getSumsq：" + stats.getSumsq() + " == " + list.stream().collect(Collectors.summingDouble(x -> x * x)));
        // 二次平均数
        System.out.println("getQuadraticMean：" + stats.getQuadraticMean() + " == " + Math.sqrt(stats.getSumsq() / list.size()));
        // 峰度
        System.out.println("getKurtosis：" + stats.getKurtosis());
        // 偏态
        System.out.println("getSkewness：" + stats.getSkewness());

    }

}
