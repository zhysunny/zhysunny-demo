package com.zhysunny.science.data.statistic;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

/**
 * 计算两组数据的皮尔逊相关系数
 * @author 章云
 * @date 2019/10/26 14:22
 */
public class PearsonTest {

    public static void main(String[] args) {
        PearsonsCorrelation pearsonsCorrelation = new PearsonsCorrelation();
        double cor = pearsonsCorrelation.correlation(DataSet.SAMPLE_X, DataSet.SAMPLE_Y);
        System.out.println(cor);
    }

}
