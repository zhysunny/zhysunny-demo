package com.zhysunny.science.weka;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 * 学习数据间的关联规则
 * @author 章云
 * @date 2019/10/30 16:19
 */
public class WekaAssociationRuleTest {

    public static void main(String[] args) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("zhysunny-science/src/main/resources/arff/supermarket.arff");
        Instances supermarket = source.getDataSet();
        Apriori apriori = new Apriori();
        apriori.buildAssociations(supermarket);
        System.out.println(apriori);
    }

}
