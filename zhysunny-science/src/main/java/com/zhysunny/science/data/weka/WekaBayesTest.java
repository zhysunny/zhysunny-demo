package com.zhysunny.science.data.weka;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.SerializationHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 使用weka的bayes算法测试模型
 * @author 章云
 * @date 2019/10/26 21:38
 */
public class WekaBayesTest {

    public static void main(String[] args) throws Exception {
        NaiveBayes nb = new NaiveBayes();
        // 这里使用训练集训练模型，也可以加载已有的模型
        // 加载模型文件
        // nb = (NaiveBayes)SerializationHelper.read("zhysunny-science/src/main/resources/iris/model/iris.model");
        // 训练数据
        BufferedReader reader = new BufferedReader(new FileReader("zhysunny-science/src/main/resources/iris/data/iris.train.arff"));
        Instances train = new Instances(reader);
        train.setClassIndex(train.numAttributes() - 1);
        // 测试数据
        reader = new BufferedReader(new FileReader("zhysunny-science/src/main/resources/iris/data/iris.test.arff"));
        Instances test = new Instances(reader);
        test.setClassIndex(train.numAttributes() - 1);
        reader.close();
        // 分类预测
        nb.buildClassifier(train);
        // 保存模型
        SerializationHelper.write("zhysunny-science/src/main/resources/iris/model/iris.train.model", nb);
        Instances labeled = new Instances(test);
        for (int i = 0; i < test.numInstances(); i++) {
            double clsLabel = nb.classifyInstance(test.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
            double[] predictOutput = nb.distributionForInstance(test.instance(i));
            double predict = predictOutput[1];
            System.out.println(predict);
        }
        // 测试结果写入arff文件中
        BufferedWriter writer = new BufferedWriter(new FileWriter("zhysunny-science/src/main/resources/iris/data/iris.test.predict.arff"));
        writer.write(labeled.toString());
        writer.close();
    }

}
