package com.zhysunny.science.statistic;

import org.apache.commons.math3.stat.inference.TestUtils;

/**
 * t检验
 * @author 章云
 * @date 2019/10/26 14:26
 */
public class TTest {

    public static void main(String[] args) {
        // 两组分布的t统计量
        System.out.println(TestUtils.pairedT(DataSet.SAMPLE_X, DataSet.SAMPLE_Y));
        // t检验p值
        System.out.println(TestUtils.pairedTTest(DataSet.SAMPLE_X, DataSet.SAMPLE_Y));
        // 给定置信区间，判断差异是否显著
        System.out.println(TestUtils.pairedTTest(DataSet.SAMPLE_X, DataSet.SAMPLE_Y, 0.05));
    }

}
