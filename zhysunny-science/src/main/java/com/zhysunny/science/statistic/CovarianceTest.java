package com.zhysunny.science.statistic;

import org.apache.commons.math3.stat.correlation.Covariance;

/**
 * 计算两组数据的协方差
 * @author 章云
 * @date 2019/10/26 14:14
 */
public class CovarianceTest {

    public static void main(String[] args) {
        // 是否有偏估计，默认为true
        double covariance = new Covariance().covariance(DataSet.SAMPLE_X, DataSet.SAMPLE_Y, false);
        System.out.println(covariance);
    }

}
