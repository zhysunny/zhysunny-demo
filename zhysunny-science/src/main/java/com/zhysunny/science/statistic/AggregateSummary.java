package com.zhysunny.science.statistic;

import org.apache.commons.math3.stat.descriptive.AggregateSummaryStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import java.util.Collections;
import java.util.List;

/**
 * 多数据生成概要统计
 * @author 章云
 * @date 2019/10/15 22:36
 */
public class AggregateSummary {

    public static void main(String[] args) {
        aggregateSummary(DataSet.LIST, DataSet.LIST1, DataSet.LIST2);
    }

    public static void aggregateSummary(List<Double>... lists) {
        // 实例化统计对象
        AggregateSummaryStatistics aggregate = new AggregateSummaryStatistics();
        for (List<Double> list : lists) {
            Collections.sort(list);
            System.out.println(list);
            SummaryStatistics summary = aggregate.createContributingStatistics();
            list.forEach(v -> summary.addValue(v));
        }
        System.out.println("getMax：" + aggregate.getMax());
        System.out.println("getMin：" + aggregate.getMin());
        System.out.println("getN：" + aggregate.getN());
        System.out.println("getMean：" + aggregate.getMean());
        System.out.println("getSum：" + aggregate.getSum());
        System.out.println("getSumsq：" + aggregate.getSumsq());
        System.out.println("getVariance：" + aggregate.getVariance());
        System.out.println("getStandardDeviation：" + aggregate.getStandardDeviation());
        // 二阶矩
        System.out.println("getSecondMoment：" + aggregate.getSecondMoment());
    }

}
