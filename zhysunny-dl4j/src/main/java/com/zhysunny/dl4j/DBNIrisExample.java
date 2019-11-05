package com.zhysunny.dl4j;

import org.deeplearning4j.datasets.iterator.DataSetIterator;
import org.deeplearning4j.datasets.iterator.impl.IrisDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.Layer;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.RBM;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.params.DefaultParamInitializer;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.api.IterationListener;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import java.util.Arrays;
import java.util.Random;

/**
 * dl4j创建深度信念神经网络
 * @author 章云
 * @date 2019/11/5 14:06
 */
public class DBNIrisExample {

    public static void main(String[] args) {
        Nd4j.MAX_SLICES_TO_PRINT = -1;
        Nd4j.MAX_ELEMENTS_PER_SLICE = -1;
        final int numRows = 4;
        final int numColumns = 1;
        int outputNum = 3;
        int numSample = 150;
        int batchSize = 150;
        int iterations = 5;
        int splitTrainNum = (int)(batchSize * 0.8);
        int seed = 123;
        int listenerFreq = 1;
        // 加载数据
        DataSetIterator iter = new IrisDataSetIterator(batchSize, numSample);
        DataSet next = iter.next();
        next.normalizeZeroMeanZeroUnitVariance();
        // 切割数据
        SplitTestAndTrain testAndTrain = next.splitTestAndTrain(splitTrainNum, new Random(seed));
        DataSet train = testAndTrain.getTrain();
        DataSet test = testAndTrain.getTest();
        Nd4j.ENFORCE_NUMERICAL_STABILITY = true;
        // 构建模型
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder().seed(seed).iterations(iterations).learningRate(1e-6f)
        .optimizationAlgo(OptimizationAlgorithm.CONJUGATE_GRADIENT).l1(1e-1).regularization(true).l2(2e-4).useDropConnect(true).list(2)
        .layer(0,
        new RBM.Builder(RBM.HiddenUnit.RECTIFIED, RBM.VisibleUnit.GAUSSIAN).nIn(numRows * numColumns).nOut(3).weightInit(WeightInit.XAVIER)
        .k(1).activation("relu").lossFunction(LossFunctions.LossFunction.RMSE_XENT).updater(Updater.ADAGRAD).dropOut(0.5).build())
        .layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.MCXENT).nIn(3).nOut(outputNum).activation("softmax").build()).build();
        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(Arrays.asList((IterationListener)new ScoreIterationListener(listenerFreq)));
        // 训练模型
        model.fit(train);
        // 评估权重
        for (Layer layer : model.getLayers()) {
            INDArray w = layer.getParam(DefaultParamInitializer.WEIGHT_KEY);
            System.out.println("Weights: " + w);
        }
        // 评估模型
        Evaluation eval = new Evaluation(outputNum);
        INDArray output = model.output(test.getFeatureMatrix());
        for (int i = 0; i < output.rows(); i++) {
            String actual = test.getLabels().getRow(i).toString().trim();
            String predicted = output.getRow(i).toString().trim();
            System.out.println("actual " + actual + " vs predicted " + predicted);
        }
        eval.eval(test.getLabels(), output);
        System.out.println(eval.stats());
    }

}
