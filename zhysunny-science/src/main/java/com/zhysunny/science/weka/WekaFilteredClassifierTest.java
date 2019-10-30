package com.zhysunny.science.weka;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.unsupervised.attribute.Remove;

/**
 * 过滤分类器
 * 随机森林模型
 * @author 章云
 * @date 2019/10/27 21:02
 */
public class WekaFilteredClassifierTest {

    public static void main(String[] args) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("zhysunny-science/src/main/resources/arff/weather.nominal.arff");
        Instances weather = source.getDataSet();
        weather.setClassIndex(weather.numAttributes() - 1);
        RandomForest rf = new RandomForest();
        Remove rm = new Remove();
        rm.setAttributeIndices("1");
        FilteredClassifier fc = new FilteredClassifier();
        fc.setFilter(rm);
        fc.setClassifier(rf);
        fc.buildClassifier(weather);
        for (int i = 0; i < weather.numInstances(); i++) {
            double pred = fc.classifyInstance(weather.instance(i));
            System.out.print("given value:" + weather.classAttribute().value((int)weather.instance(i).classValue()));
            System.out.println("---predicted value:" + weather.classAttribute().value((int)pred));
        }
    }

}
