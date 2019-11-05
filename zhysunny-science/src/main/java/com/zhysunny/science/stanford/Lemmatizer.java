package com.zhysunny.science.stanford;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.Properties;

/**
 * corenlp提取词根、词性、命名实体识别
 * 注意stanford-classifier-3.6.0.jar冲突
 * @author 章云
 * @date 2019/11/4 16:23
 */
public class Lemmatizer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("annotators", "tokenize,ssplit,pos,lemma,ner");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props, false);
        String text = "Hamlet's mother, Queen Gertrude, says this famous line while watching The Mousetrap. Gertrude is talking about the queen in the play. She feels that the play-queen seems insincere because she repeats so dramatically that she'll never remarry due to her undying love of her husband.";
        Annotation document = pipeline.process(text);
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                System.out.println(word + " --> " + lemma + " --> " + pos + " --> " + ner);
            }
        }
    }

}
