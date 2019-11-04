package com.zhysunny.science.mulan;

import mulan.classifier.lazy.MLkNN;
import mulan.classifier.meta.RAkEL;
import mulan.classifier.transformation.LabelPowerset;
import mulan.data.MultiLabelInstances;
import mulan.evaluation.Evaluator;
import mulan.evaluation.MultipleEvaluation;
import weka.classifiers.trees.J48;
/**
 * Mulan对多标签数据点进行分类
 * 需要3.7.10的weka才能跑通
 * @author 章云
 * @date 2019/10/31 18:13
 */
public class Mulan {

    public static void main(String[] args) throws Exception {
        MultiLabelInstances dataset = new MultiLabelInstances("zhysunny-science/src/main/resources/mulan/emotions.arff",
        "zhysunny-science/src/main/resources/mulan/emotions.xml");
        RAkEL learner1 = new RAkEL(new LabelPowerset(new J48()));
        MLkNN learner2 = new MLkNN();
        Evaluator eval = new Evaluator();
        MultipleEvaluation results;
        int numFolds = 10;
        results = eval.crossValidate(learner1, dataset, numFolds);
        System.out.println(results);
        results = eval.crossValidate(learner2, dataset, numFolds);
        System.out.println(results);
    }

}
