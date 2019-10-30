package com.zhysunny.science.weka;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 * 线性回归模型
 * @author 章云
 * @date 2019/10/30 15:56
 */
public class WekaLinearRegressionTest {

    public static void main(String[] args) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("zhysunny-science/src/main/resources/arff/cpu.arff");
        Instances cpu = source.getDataSet();
        cpu.setClassIndex(cpu.numAttributes() - 1);
        LinearRegression lReg = new LinearRegression();
        lReg.buildClassifier(cpu);
        System.out.println(lReg);
    }

}
