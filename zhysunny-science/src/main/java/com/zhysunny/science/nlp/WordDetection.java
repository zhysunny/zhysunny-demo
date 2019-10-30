package com.zhysunny.science.nlp;

import java.text.BreakIterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检测单词
 * @author 章云
 * @date 2019/10/30 22:25
 */
public class WordDetection {

    private static final Pattern PATTERN = Pattern.compile("\\w[\\w-]+('\\w*)?");

    public static void main(String[] args) {
        String input = "Let's get this vis-a-vis,he said,these boys'marks are really that well?";
        WordDetection wordDetection = new WordDetection();
        wordDetection.useTokenizer(input);
        wordDetection.useBreakIterator(input);
        wordDetection.useRegEx(input);
    }

    public void useTokenizer(String input) {
        System.out.println("Tokenizer");
        StringTokenizer tokenizer = new StringTokenizer(input, " ,");
        while (tokenizer.hasMoreElements()) {
            System.out.println(tokenizer.nextToken());
        }
    }

    public void useBreakIterator(String input) {
        System.out.println("Break Iterator");
        BreakIterator iterator = BreakIterator.getWordInstance();
        iterator.setText(input);
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            System.out.println(input.substring(start, end));
        }
    }

    public void useRegEx(String input) {
        System.out.println("Regular Expression");
        Matcher matcher = PATTERN.matcher(input);
        while (matcher.find()) {
            System.out.println(input.substring(matcher.start(), matcher.end()));
        }
    }

}
