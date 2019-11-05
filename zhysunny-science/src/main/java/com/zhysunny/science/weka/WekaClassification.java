package com.zhysunny.science.weka;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.TextDirectoryLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * weka对文本文档分类
 * 没有合适的数据测试
 * @author 章云
 * @date 2019/11/4 18:09
 */
public class WekaClassification {

    public static void main(String[] args) throws Exception {
        TextDirectoryLoader loader = new TextDirectoryLoader();
        loader.setDirectory(new File(""));
        Instances data = loader.getDataSet();

        StringToWordVector filter = new StringToWordVector();
        filter.setInputFormat(data);
        Instances dataFiltered = Filter.useFilter(data, filter);

        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(dataFiltered);
        System.out.println(nb);
        Evaluation eval = new Evaluation(dataFiltered);
        eval.crossValidateModel(nb, dataFiltered, 5, new Random(1));
        System.out.println(eval.toSummaryString());
    }

}
