package com.zhysunny.dl4j;

import org.deeplearning4j.datasets.fetchers.MnistDataFetcher;
import org.deeplearning4j.datasets.iterator.DataSetIterator;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.RBM;
import java.io.IOException;
import java.util.Arrays;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.api.IterationListener;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.lossfunctions.LossFunctions;

/**
 * dl4j创建深度自动编码器
 * @author 章云
 * @date 2019/11/5 14:31
 */
public class DeepAutoEncoderExample {

    public static void main(String[] args) throws IOException {
        final int numRows = 28;
        final int numColumns = 28;
        int seed = 123;
        int numSample = MnistDataFetcher.NUM_EXAMPLES;
        int batchSize = 1000;
        int iterations = 1;
        int listenerFreq = iterations / 5;
        // 加载数据
        DataSetIterator iter = new MnistDataSetIterator(batchSize, numSample);
        // 构建模型
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder().seed(seed).iterations(iterations)
        .optimizationAlgo(OptimizationAlgorithm.LINE_GRADIENT_DESCENT).list(10)
        .layer(0, new RBM.Builder().nIn(numRows * numColumns).nOut(1000).lossFunction(LossFunctions.LossFunction.RMSE_XENT).build())
        .layer(1, new RBM.Builder().nIn(1000).nOut(500).lossFunction(LossFunctions.LossFunction.RMSE_XENT).build())
        .layer(2, new RBM.Builder().nIn(500).nOut(250).lossFunction(LossFunctions.LossFunction.RMSE_XENT).build())
        .layer(3, new RBM.Builder().nIn(250).nOut(100).lossFunction(LossFunctions.LossFunction.RMSE_XENT).build())
        .layer(4, new RBM.Builder().nIn(100).nOut(30).lossFunction(LossFunctions.LossFunction.RMSE_XENT).build())
        // 编码停止
        .layer(5, new RBM.Builder().nIn(30).nOut(100).lossFunction(LossFunctions.LossFunction.RMSE_XENT).build())
        // 解码开始
        .layer(6, new RBM.Builder().nIn(100).nOut(250).lossFunction(LossFunctions.LossFunction.RMSE_XENT).build())
        .layer(7, new RBM.Builder().nIn(250).nOut(500).lossFunction(LossFunctions.LossFunction.RMSE_XENT).build())
        .layer(8, new RBM.Builder().nIn(500).nOut(1000).lossFunction(LossFunctions.LossFunction.RMSE_XENT).build())
        .layer(9, new RBM.Builder().nIn(1000).nOut(numRows * numColumns).lossFunction(LossFunctions.LossFunction.RMSE_XENT).build())
        .pretrain(true).backprop(true).build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(Arrays.asList((IterationListener)new ScoreIterationListener(listenerFreq)));
        // 训练模型
        while (iter.hasNext()) {
            DataSet next = iter.next();
            model.fit(new DataSet(next.getFeatureMatrix(), next.getFeatureMatrix()));
        }
    }

}
