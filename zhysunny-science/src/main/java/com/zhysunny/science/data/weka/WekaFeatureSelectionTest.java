package com.zhysunny.science.data.weka;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import java.util.Random;

/**
 * 特征选择
 * @author 章云
 * @date 2019/10/30 16:23
 */
public class WekaFeatureSelectionTest {

    public static void main(String[] args) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("zhysunny-science/src/main/resources/arff/iris.arff");
        Instances iris = source.getDataSet();
        iris.setClassIndex(iris.numAttributes() - 1);
        // 低层方法选择特征
        AttributeSelection attributeSelection = new AttributeSelection();
        attributeSelection.setEvaluator(new CfsSubsetEval());
        attributeSelection.setSearch(new BestFirst());
        attributeSelection.SelectAttributes(iris);
        int[] ints = attributeSelection.selectedAttributes();
        System.out.println("---------- 低层方法选择特征 ----------");
        System.out.println(Utils.arrayToString(ints));
        // 过滤器方法选择特征
        weka.filters.supervised.attribute.AttributeSelection filter = new weka.filters.supervised.attribute.AttributeSelection();
        filter.setEvaluator(new CfsSubsetEval());
        filter.setSearch(new BestFirst());
        filter.setInputFormat(iris);
        Instances newData = Filter.useFilter(iris, filter);
        System.out.println("---------- 过滤器方法选择特征 ----------");
        System.out.println(newData);
        // 元分类器选择特征
        AttributeSelectedClassifier classifier = new AttributeSelectedClassifier();
        classifier.setClassifier(new NaiveBayes());
        classifier.setEvaluator(new CfsSubsetEval());
        classifier.setSearch(new BestFirst());
        Evaluation evaluation = new Evaluation(iris);
        evaluation.crossValidateModel(classifier, iris, 10, new Random(1));
        System.out.println("---------- 元分类器选择特征 ----------");
        System.out.println(evaluation.toSummaryString());
    }

}
