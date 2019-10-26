package com.zhysunny.java.structure.data.tree.huffman;

import java.util.Iterator;
import java.util.Map;

/**
 * 测试类
 * @author 章云
 * @date 2019/9/21 11:36
 */
public class TestTree {

    public static void main(String[] args) throws Exception {
        PriorityQueue queue = new PriorityQueue();
        // SUSIE SAYS IT IS EASY
        // if代表换行符，sp代表空格
        Node n9 = new Node(5, "S");
        Node n1 = new Node(1, "if");
        Node n2 = new Node(1, "U");
        Node n3 = new Node(1, "T");
        Node n4 = new Node(2, "Y");
        Node n5 = new Node(2, "E");
        Node n6 = new Node(2, "A");
        Node n7 = new Node(3, "I");
        Node n8 = new Node(4, "sp");
        queue.insert(n3);
        queue.insert(n2);
        queue.insert(n1);
        queue.insert(n6);
        queue.insert(n5);
        queue.insert(n4);
        queue.insert(n7);
        queue.insert(n8);
        queue.insert(n9);
        queue.display();

        HuffmanTree tree = queue.buildHuffmanTree();
        Map<String, String> map = tree.getCodeSet();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        System.out.println("霍夫曼编码结果：");
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey() + "——>" + entry.getValue());
        }
    }

}
