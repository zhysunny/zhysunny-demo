package com.zhysunny.science.statistic;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 章云
 * @date 2019/10/23 15:51
 */
public class WordCount {

    public static void main(String[] args) {
        String str = "java python scala shell java hadoop scala spark scala kafka";
        // 不好做排序
        Map<String, Long> collect = Arrays.stream(str.split(" ")).collect(Collectors.groupingBy(String::toString, Collectors.counting()));
        System.out.println(collect);
    }

}
