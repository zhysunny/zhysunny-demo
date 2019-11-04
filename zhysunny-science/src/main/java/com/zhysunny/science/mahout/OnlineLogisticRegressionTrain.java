package com.zhysunny.science.mahout;

import org.apache.mahout.classifier.sgd.CsvRecordFactory;
import org.apache.mahout.classifier.sgd.LogisticModelParameters;
import org.apache.mahout.classifier.sgd.OnlineLogisticRegression;
import org.apache.mahout.math.RandomAccessSparseVector;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import org.apache.mahout.math.Vector;

/**
 * mahout训练在线逻辑回归模型
 * @author 章云
 * @date 2019/11/4 18:29
 */
public class OnlineLogisticRegressionTrain {

    public static void main(String[] args) throws IOException {
        String inputFile = "zhysunny-science/src/main/resources/mahout/weather.numeric.csv";
        String modelFile = "zhysunny-science/src/main/resources/mahout/weather.numeric.model";

        List<String> features = Arrays.asList("outlook", "temperature", "humidity", "windy", "play");
        List<String> featureType = Arrays.asList("w", "n", "n", "w", "w");
        LogisticModelParameters params = new LogisticModelParameters();
        params.setTargetVariable("play");
        params.setMaxTargetCategories(2);
        params.setNumFeatures(4);
        params.setUseBias(false);
        params.setTypeMap(features, featureType);
        params.setLearningRate(0.5);

        int passes = 10;
        CsvRecordFactory csv = params.getCsvRecordFactory();
        OnlineLogisticRegression olr = params.createRegression();

        for (int pass = 0; pass < passes; pass++) {
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            csv.firstLine(in.readLine());
            String row = in.readLine();
            while (row != null) {
                System.out.println(row);
                Vector input = new RandomAccessSparseVector(params.getNumFeatures());
                int targetValue = csv.processLine(row, input);
                olr.train(targetValue, input);
                row = in.readLine();
            }
            in.close();
        }
        OutputStream os = new FileOutputStream(modelFile);
        params.saveTo(os);
        os.close();
    }

}
