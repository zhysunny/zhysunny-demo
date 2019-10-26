package com.zhysunny.science.data.statistic;

import org.apache.commons.math3.stat.inference.TestUtils;

/**
 * K-S检验
 * @author 章云
 * @date 2019/10/26 14:38
 */
public class KSTest {

    public static void main(String[] args) {
        // d统计量
        double d = TestUtils.kolmogorovSmirnovStatistic(DataSet.SAMPLE_X, DataSet.SAMPLE_Y);
        System.out.println(d);
        // 评估零假设
        System.out.println(TestUtils.kolmogorovSmirnovTest(DataSet.SAMPLE_X, DataSet.SAMPLE_Y, false));
        // 显著性检验的P值
        System.out.println(TestUtils.exactP(d, DataSet.SAMPLE_X.length, DataSet.SAMPLE_Y.length, false));
    }

}
