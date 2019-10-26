package com.zhysunny.science.data.statistic;

import org.apache.commons.math3.stat.inference.TestUtils;

/**
 * 卡方检验
 * @author 章云
 * @date 2019/10/26 14:30
 */
public class ChiSquareTest {

    public static void main(String[] args) {
        // 卡方检验的t统计量
        System.out.println(TestUtils.chiSquare(DataSet.SAMPLE_Y, DataSet.SAMPLE_Z));
        // 卡方检验的p值
        System.out.println(TestUtils.chiSquareTest(DataSet.SAMPLE_Y, DataSet.SAMPLE_Z));
        // 给定置信区间，判断差异是否显著
        System.out.println(TestUtils.chiSquareTest(DataSet.SAMPLE_Y, DataSet.SAMPLE_Z, 0.05));
    }

}
