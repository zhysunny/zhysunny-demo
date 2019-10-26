package com.zhysunny.java.structure.data.tree.huffman;

import java.util.Iterator;
import java.util.Map;

/**
 * 霍夫曼解码器
 * @author 章云
 * @date 2019/9/21 11:37
 */
public class HuffmanDecoder {

    private Map<String, String> codeSet;  //代码段对应的代码集

    public HuffmanDecoder(Map<String, String> map) {
        codeSet = map;
    }

    //将代码段解析成消息文本
    public String decode(String code) {
        String message = "";
        String key = "";
        char[] chars = code.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            key += chars[i];
            if (codeSet.containsValue(key)) {  //代码集中存在该段代码
                Iterator<Map.Entry<String, String>> it = codeSet.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = it.next();
                    if (entry.getValue().equals(key)) {
                        message += entry.getKey();  //获取该段代码对应的键值，即消息字符
                    }
                }
                key = "";  //代码段变量置为0
            } else {
                continue;  //该段代码不能解析为文本消息，继续循环
            }
        }
        return message;
    }

}
