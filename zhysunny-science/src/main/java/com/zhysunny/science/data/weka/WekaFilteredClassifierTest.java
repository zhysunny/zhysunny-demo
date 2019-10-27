package com.zhysunny.science.data.weka;

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
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("zhysunny-science/src/main/resources/iris/data/iris.arff");
        Instances iris = source.getDataSet();
        iris.setClassIndex(iris.numAttributes() - 1);
        RandomForest rf = new RandomForest();
        Remove rm = new Remove();
        rm.setAttributeIndices("1");
        FilteredClassifier fc = new FilteredClassifier();
        fc.setFilter(rm);
        fc.setClassifier(rf);
        fc.buildClassifier(iris);
        for (int i = 0; i < iris.numInstances(); i++) {
            double pred = fc.classifyInstance(iris.instance(i));
            System.out.print("given value:" + iris.classAttribute().value((int)iris.instance(i).classValue()));
            System.out.println("---predicted value:" + iris.classAttribute().value((int)pred));
        }
    }

}
