package com.zhysunny.dl4j;

import org.deeplearning4j.models.embeddings.WeightLookupTable;
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.InMemoryLookupCache;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.UimaSentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import java.util.ArrayList;
import java.util.Collection;

/**
 * dl4j创建word2vec神经网络
 * @author 章云
 * @date 2019/11/5 13:51
 */
public class Word2VecRawTextExample {

    public static void main(String[] args) throws Exception {
        String path = "zhysunny-dl4j/src/main/resources/word2vec/raw_sentences.txt";
        // 去除每行前后的空格
        SentenceIterator iter = UimaSentenceIterator.createWithPath(path);
        // 根据数据行中的空格进行切分，获取单词
        TokenizerFactory token = new DefaultTokenizerFactory();
        token.setTokenPreProcessor(new CommonPreprocessor());

        InMemoryLookupCache cache = new InMemoryLookupCache();
        WeightLookupTable table = new InMemoryLookupTable.Builder().vectorLength(100).useAdaGrad(false).cache(cache).lr(0.025f).build();
        // 创建模型
        Word2Vec vec = new Word2Vec.Builder().minWordFrequency(5).iterations(1).layerSize(100).lookupTable(table)
        .stopWords(new ArrayList<>()).vocabCache(cache).seed(42).windowSize(5).iterate(iter).tokenizerFactory(token).build();
        // 拟合模型
        vec.fit();
        // 写单词
        WordVectorSerializer.writeWordVectors(vec, "zhysunny-dl4j/src/main/resources/word2vec/word2vec.txt");

        Collection<String> lst = vec.wordsNearest("man", 5);
        System.out.println(lst);
        double cosSim = vec.similarity("cruise", "voyage");
        System.out.println(cosSim);
    }

}
