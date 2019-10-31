package com.zhysunny.science.moa;

import com.yahoo.labs.samoa.instances.Instance;
import moa.classifiers.Classifier;
import moa.classifiers.trees.HoeffdingTree;
import moa.core.TimingUtils;
import moa.streams.generators.RandomRBFGenerator;

/**
 * moa对数据点分类
 * @author 章云
 * @date 2019/10/31 10:24
 */
public class MOA {

    public static void main(String[] args) {
        Classifier learner = new HoeffdingTree();
        RandomRBFGenerator stream = new RandomRBFGenerator();
        stream.prepareForUse();
        learner.setModelContext(stream.getHeader());
        learner.prepareForUse();
        int correct = 0;
        int sample = 0;
        long eval = TimingUtils.getNanoCPUTimeOfCurrentThread();
        while (stream.hasMoreInstances() && sample < 1000000) {
            Instance train = stream.nextInstance().getData();
            if (learner.correctlyClassifies(train)) {
                correct++;
            }
            sample++;
            learner.trainOnInstance(train);
        }
        double accuracy = 100.0 * (double)correct / (double)sample;
        double time = TimingUtils.nanoTimeToSeconds(TimingUtils.getNanoCPUTimeOfCurrentThread() - eval);
        System.out.println(sample + " instances processed with " + accuracy + "% accuracy in " + time + " seconds.");
    }

}
