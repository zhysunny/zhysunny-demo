package com.zhysunny.science.stanford;

import edu.stanford.nlp.classify.Classifier;
import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.classify.LinearClassifier;
import edu.stanford.nlp.ling.Datum;
import edu.stanford.nlp.objectbank.ObjectBank;
import edu.stanford.nlp.util.ErasureUtils;
import java.io.*;

/**
 * 斯坦福分类器对数据点分类
 * @author 章云
 * @date 2019/10/31 10:01
 */
public class StanfordClassifier {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ColumnDataClassifier cdc = new ColumnDataClassifier("zhysunny-science/src/main/resources/stanford/cheese2007.prop");
        Classifier<String, String> cl = cdc
        .makeClassifier(cdc.readTrainingExamples("zhysunny-science/src/main/resources/stanford/cheeseDisease.train"));
        for (String line : ObjectBank.getLineIterator("zhysunny-science/src/main/resources/stanford/cheeseDisease.test", "utf-8")) {
            Datum<String, String> d = cdc.makeDatumFromLine(line);
            System.out.println(line + "  ==>  " + cl.classOf(d));
        }
        demonstrateSerialization();
    }

    public static void demonstrateSerialization() throws IOException, ClassNotFoundException {
        System.out.println("Demonstrating working with a serialized classifier");
        ColumnDataClassifier cdc = new ColumnDataClassifier("zhysunny-science/src/main/resources/stanford/cheese2007.prop");
        Classifier<String, String> cl = cdc.makeClassifier(cdc.readTrainingExamples("zhysunny-science/src/main/resources/stanford/cheeseDisease.train"));
        System.out.println();
        System.out.println();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(cl);
        oos.close();
        byte[] object = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(object);
        ObjectInputStream ois = new ObjectInputStream(bais);
        LinearClassifier<String, String> lc = ErasureUtils.uncheckedCast(ois.readObject());
        ois.close();
        ColumnDataClassifier cdc2 = new ColumnDataClassifier("zhysunny-science/src/main/resources/stanford/cheese2007.prop");
        for (String line : ObjectBank.getLineIterator("zhysunny-science/src/main/resources/stanford/cheeseDisease.test", "utf-8")) {
            Datum<String, String> d = cdc.makeDatumFromLine(line);
            Datum<String, String> d2 = cdc2.makeDatumFromLine(line);
            System.out.println(line + "  =origi=>  " + cl.classOf(d));
            System.out.println(line + "  =deser=>  " + lc.classOf(d2));
        }
    }

}
