package com.zhysunny.science.weka;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import java.io.*;
import java.net.URL;

/**
 * 生成arff文件
 * 将iris.data转为iris.arff
 * @author 章云
 * @date 2019/10/26 20:54
 */
public class WekaArffTest {

    public static void main(String[] args) throws IOException {
        // 分类标签
        FastVector classVals = new FastVector();
        classVals.addElement("Iris-setosa");
        classVals.addElement("Iris-versicolor");
        classVals.addElement("Iris-virginica");
        // 属性
        FastVector attributes = new FastVector();
        attributes.addElement(new Attribute("sepallength"));
        attributes.addElement(new Attribute("sepalwidth"));
        attributes.addElement(new Attribute("petallength"));
        attributes.addElement(new Attribute("petalwidth"));
        attributes.addElement(new Attribute("class", classVals));
        // 创建实例
        Instances data = new Instances("iris", attributes, 0);
        double[] values;
        // 读取数据
        URL url = WekaArffTest.class.getClassLoader().getResource("data/iris.data");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = br.readLine()) != null) {
            String[] split = line.split(",");
            if (split.length == 5) {
                values = new double[5];
                values[0] = Double.parseDouble(split[0]);
                values[1] = Double.parseDouble(split[1]);
                values[2] = Double.parseDouble(split[2]);
                values[3] = Double.parseDouble(split[3]);
                values[4] = classVals.indexOf(split[4]);
                data.add(new Instance(1.0, values));
            }
        }
        br.close();
        // 把arff文件写到磁盘
        BufferedWriter writer = new BufferedWriter(new FileWriter("zhysunny-science/src/main/resources/iris/data/iris.arff"));
        writer.write(data.toString());
        writer.close();
        System.out.println(data);
    }

}
