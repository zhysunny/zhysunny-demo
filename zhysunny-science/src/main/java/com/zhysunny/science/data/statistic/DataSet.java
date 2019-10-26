package com.zhysunny.science.data.statistic;

import java.util.Arrays;
import java.util.List;

/**
 * 模拟数据集
 * @author 章云
 * @date 2019/10/15 22:37
 */
public class DataSet {

    public static final Double[] ARRAY = new Double[]{ 1.0, 3.0, 4.0, 5.0, 6.0, 8.0, 10.0, 14.0 };
    public static final Double[] ARRAY1 = new Double[]{ 3.0, 9.0, 4.0, 5.0, 7.0, 10.0, 15.0, 24.0 };
    public static final Double[] ARRAY2 = new Double[]{ 1.0, 4.0, 4.0, 7.0, 6.0, 16.0, 14.0, 17.0 };

    public static final List<Double> LIST = Arrays.asList(ARRAY);
    public static final List<Double> LIST1 = Arrays.asList(ARRAY1);
    public static final List<Double> LIST2 = Arrays.asList(ARRAY2);

    /**
     * 简单回归数据集
     */
    public static final double[][] REGRESSION = { { 1, 3 }, { 2, 5 }, { 3, 7 }, { 4, 14 }, { 5, 11 } };
    /**
     * 普通最小二乘法
     */
    public static final double[][] X = { { 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0 }, { 0, 3, 0, 0, 0 }, { 0, 0, 4, 0, 0 }, { 0, 0, 0, 5, 0 },
    { 0, 0, 0, 0, 6 } };
    public static final double[] Y = { 11, 12, 13, 14, 15, 16 };
    public static final double[][] OMEGA = { { 1.1, 0, 0, 0, 0, 0 }, { 0, 2.2, 0, 0, 0, 0 }, { 0, 0, 3.3, 0, 0, 0 }, { 0, 0, 0, 4.4, 0, 0 },
    { 0, 0, 0, 0, 5.5, 0 }, { 0, 0, 0, 0, 0, 6.6 } };

    public static final double[] SAMPLE_X = { 43, 21, 25, 42, 57, 59 };
    public static final double[] SAMPLE_Y = { 99, 65, 79, 75, 87, 81 };
    public static final long[] SAMPLE_Z = { 43, 21, 25, 42, 57, 59 };

}
