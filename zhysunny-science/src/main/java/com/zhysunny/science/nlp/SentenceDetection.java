package com.zhysunny.science.nlp;

import java.text.BreakIterator;
import java.util.Locale;

/**
 * 检测句子
 * @author 章云
 * @date 2019/10/30 22:38
 */
public class SentenceDetection {

    public static void main(String[] args) {
        SentenceDetection sentenceDetection = new SentenceDetection();
        String source = "My name is Rushdi Shams. You can use Dr. before my name as I have a Ph.D. but I am a bit shy to use it.";
        sentenceDetection.useSentenceIterator(source);
    }

    public void useSentenceIterator(String source) {
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(source);
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            System.out.println(source.substring(start, end));
        }
    }

}
