package com.zhysunny.science.data.weka;

import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 * 逻辑回归模型
 * @author 章云
 * @date 2019/10/30 15:56
 */
public class WekaLogisticRegressionTest {

    public static void main(String[] args) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("zhysunny-science/src/main/resources/arff/iris.arff");
        Instances iris = source.getDataSet();
        iris.setClassIndex(iris.numAttributes() - 1);
        Logistic logReg = new Logistic();
        logReg.buildClassifier(iris);
        System.out.println(logReg);
    }

}
