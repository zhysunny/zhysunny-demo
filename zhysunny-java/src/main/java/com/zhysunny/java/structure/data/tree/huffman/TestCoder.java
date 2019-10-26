package com.zhysunny.java.structure.data.tree.huffman;

/**
 * 测试类
 * @author 章云
 * @date 2019/9/21 11:38
 */
public class TestCoder {

    public static void main(String[] args) {

        String message = "chen long fei is hero !";
        HuffmanEncoder encoder = new HuffmanEncoder();
        String code = encoder.encode(message);

        encoder.printCodeSet();
        System.out.print("编码结果：");
        System.out.println(code.getBytes());

        HuffmanDecoder decoder = new HuffmanDecoder(encoder.getCodeSet());
        String message2 = decoder.decode(code);
        System.out.print("解码结果：");
        System.out.println(message2);
    }

}
