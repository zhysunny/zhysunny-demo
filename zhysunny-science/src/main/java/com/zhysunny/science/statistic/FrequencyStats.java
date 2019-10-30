package com.zhysunny.science.statistic;

import org.apache.commons.math3.stat.Frequency;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 计算频率分布
 * @author 章云
 * @date 2019/10/15 23:01
 */
public class FrequencyStats {

    public static void main(String[] args) {
        frequency(DataSet.LIST2);
    }

    public static void frequency(List<Double> list) {
        Collections.sort(list);
        System.out.println(list);
        Frequency stats = new Frequency();
        list.forEach(v -> stats.addValue(v));
        list.forEach(v -> System.out.println(stats.getCount(v)));
        // wordcount
        Map<Double, Long> collect = list.stream().collect(Collectors.groupingBy(Double::doubleValue, Collectors.counting()));
        System.out.println(collect);
    }

}
