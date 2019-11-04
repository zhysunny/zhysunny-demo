package com.zhysunny.science.nlp;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 计算句子相似度
 * @author 章云
 * @date 2019/11/4 16:35
 */
public class CosineSimilarity {

    public static void main(String[] args) {
        String s1 = "To be, or bot to be: that is the question.";
        String s2 = "Frailty, thy name is woman!";
        String s3 = "The lady doth protest too much, methinks.";
        String s4 = "Frailty, thy name is woman!";
        CosineSimilarity cos = new CosineSimilarity();
        System.out.println(cos.calculateCosine(s1, s2));
        System.out.println(cos.calculateCosine(s3, s4));
        System.out.println(cos.calculateCosine(s2, s4));
        System.out.println(cos.calculateCosine(s1, s3));
    }

    public double calculateCosine(String s1, String s2) {
        // 分词
        Stream<String> stream1 = Stream.of(s1.toLowerCase().split("\\W+")).parallel();
        Stream<String> stream2 = Stream.of(s2.toLowerCase().split("\\W+")).parallel();
        // 计算词频
        Map<String, Long> wordFreq1 = stream1.collect(Collectors.groupingBy(String::toString, Collectors.counting()));
        Map<String, Long> wordFreq2 = stream2.collect(Collectors.groupingBy(String::toString, Collectors.counting()));
        // 每个字符串的特有单词
        Set<String> wordSet1 = wordFreq1.keySet();
        Set<String> wordSet2 = wordFreq2.keySet();
        // 两个字符串的共用单词
        Set<String> intersection = new HashSet<>(wordSet1);
        intersection.retainAll(wordSet2);
        // 余弦公式
        double numerator = 0;
        for (String common : intersection) {
            numerator += wordFreq1.get(common) * wordFreq2.get(common);
        }
        double param1 = 0, param2 = 0;
        for (String w1 : wordSet1) {
            param1 += Math.pow(wordFreq1.get(w1), 2);
        }
        param1 = Math.sqrt(param1);
        for (String w2 : wordSet2) {
            param2 += Math.pow(wordFreq2.get(w2), 2);
        }
        param2 = Math.sqrt(param2);
        double denominator = param1 * param2;
        return numerator / denominator;
    }

}
