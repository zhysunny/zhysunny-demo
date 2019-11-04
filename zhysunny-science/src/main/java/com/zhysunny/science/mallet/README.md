# mallet使用说明

将mallet解压，我的解压目录是F:\Desktop\javaml\mallet-2.0.8，可以设置环境变量

## 从文本中提取主题

首先将文本文件转为单个文件

    mallet import-dir --input F:\Desktop\javaml\mallet-2.0.8\sample-data\web\en --output F:\Desktop\javaml\web.en.mallet --keep-sequence --remove-stopwords

针对web.en.mallet文件执行主题建模

    mallet train-topics --input F:\Desktop\javaml\web.en.mallet
    
针对web.en.mallet文件执行主题建模，增加更多的参数

    // 最多生成20个主题，报告输出到xml中
    mallet train-topics --input F:\Desktop\javaml\web.en.mallet --num-topics 20 --num-top-words 20 --optimize-interval 10 --xml-topic-phrase-report F:\Desktop\javaml\web.en.xml
    // alpha平滑参数
    mallet train-topics --input F:\Desktop\javaml\web.en.mallet --num-topics 20 --num-top-words 20 --optimize-interval 10 --alpha 2.5 --xml-topic-phrase-report F:\Desktop\javaml\web.en.xml
    // 不同格式输出
    // F:\Desktop\javaml\web.en.gz 语料库中每个单词及其所属主题
    // F:\Desktop\javaml\web.en.keys.txt 每个主题的编号、权重、热门关键词
    // F:\Desktop\javaml\web.en.composition.txt 包含着导入的每个原始文本文件中各个主题的拆解明细
    mallet train-topics --input F:\Desktop\javaml\web.en.mallet --num-topics 20 --num-top-words 20 --optimize-interval 10 --output-state F:\Desktop\javaml\web.en.gz --output-topic-keys F:\Desktop\javaml\web.en.keys.txt --output-doc-topics F:\Desktop\javaml\web.en.composition.txt
 
