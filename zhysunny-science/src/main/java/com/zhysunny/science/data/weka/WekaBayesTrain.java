package com.zhysunny.science.data.weka;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;
import java.util.Random;

/**
 * 使用weka的bayes算法训练模型
 * @author 章云
 * @date 2019/10/26 21:38
 */
public class WekaBayesTrain {

    public static void main(String[] args) throws Exception {
        // 加载arff文件
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("zhysunny-science/src/main/resources/iris/data/iris.arff");
        Instances iris = source.getDataSet();
        if (iris.classIndex() == -1) {
            // 设置分类标签位置
            iris.setClassIndex(iris.numAttributes() - 1);
        }
        // 创建bayes分类模型
        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(iris);
        // 保存模型
        SerializationHelper.write("zhysunny-science/src/main/resources/iris/model/iris.model", nb);
        // 交叉验证
        Evaluation eval = new Evaluation(iris);
        eval.crossValidateModel(nb, iris, 10, new Random(1));
        System.out.println(eval.toSummaryString());
    }

}
