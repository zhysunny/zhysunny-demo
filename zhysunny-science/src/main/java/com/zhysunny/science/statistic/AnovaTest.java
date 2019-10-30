package com.zhysunny.science.statistic;

import org.apache.commons.math3.stat.inference.TestUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * 单因素方差分析
 * @author 章云
 * @date 2019/10/26 14:33
 */
public class AnovaTest {

    public static void main(String[] args) {
        double[] calorie = { 8, 9, 6, 7, 3 };
        double[] fat = { 2, 4, 3, 5, 1 };
        double[] carb = { 3, 5, 4, 2, 3 };
        double[] control = { 2, 2, -1, 0, 3 };
        List<double[]> classes = new ArrayList<>();
        classes.add(calorie);
        classes.add(fat);
        classes.add(carb);
        classes.add(control);
        // 单因素方差分析F值
        System.out.println(TestUtils.oneWayAnovaFValue(classes));
        // 单因素方差分析P值
        System.out.println(TestUtils.oneWayAnovaPValue(classes));
        // 判断四个类别的数据点之间的差异是否显著
        System.out.println(TestUtils.oneWayAnovaTest(classes, 0.05));
    }

}
