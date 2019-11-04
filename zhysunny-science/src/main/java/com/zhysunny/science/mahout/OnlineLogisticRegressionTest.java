package com.zhysunny.science.mahout;

import org.apache.commons.io.Charsets;
import org.apache.mahout.classifier.evaluation.Auc;
import org.apache.mahout.classifier.sgd.CsvRecordFactory;
import org.apache.mahout.classifier.sgd.LogisticModelParameters;
import org.apache.mahout.classifier.sgd.OnlineLogisticRegression;
import org.apache.mahout.math.Matrix;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.SequentialAccessSparseVector;
import org.apache.mahout.math.Vector;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * mahout应用在线逻辑回归模型
 * @author 章云
 * @date 2019/11/4 18:29
 */
public class OnlineLogisticRegressionTest {

    public static void main(String[] args) throws IOException {
        String inputFile = "zhysunny-science/src/main/resources/mahout/weather.numeric.test.csv";
        String modelFile = "zhysunny-science/src/main/resources/mahout/weather.numeric.model";

        Auc auc = new Auc();
        LogisticModelParameters params = LogisticModelParameters.loadFrom(new File(modelFile));
        CsvRecordFactory csv = params.getCsvRecordFactory();
        OnlineLogisticRegression olr = params.createRegression();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        String line = br.readLine();
        csv.firstLine(line);
        line = br.readLine();
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), true);
        output.println("class,model-output,log-likelihood");
        while (line != null) {
            Vector vector = new SequentialAccessSparseVector(params.getNumFeatures());
            int classValue = csv.processLine(line, vector);
            double score = olr.classifyScalarNoLink(vector);
            output.printf(Locale.ENGLISH, "%d,%.3f,%.6f%n", classValue, score, olr.logLikelihood(classValue, vector));
            auc.add(classValue, score);
            line = br.readLine();
        }
        br.close();
        output.printf(Locale.ENGLISH, "Auc = %.2f%n", auc.auc());
        Matrix matrix = auc.confusion();
        output.printf(Locale.ENGLISH, "confusion:[[%.1f, %.1f], [%.1f, %.1f]]%n", matrix.get(0, 0), matrix.get(1, 0), matrix.get(0, 1),
        matrix.get(1, 1));
        matrix = auc.entropy();
        output.printf(Locale.ENGLISH, "entropy:[[%.1f, %.1f], [%.1f, %.1f]]%n", matrix.get(0, 0), matrix.get(1, 0), matrix.get(0, 1),
        matrix.get(1, 1));
    }

}
