package com.zhysunny.science.data.get;

/**
 * 清洗文本数据
 * @author 章云
 * @date 2019/9/28 20:20
 */
public class CleanText {

    public static void main(String[] args) {

    }

    /**
     * 文本内容清洗
     * @param text
     */
    public static void cleanAscii(String text) {
        // 去掉非ASCII字符
        text = text.replaceAll("[^p{ASCII}]", "");
        // 连续空格用单个空格表示
        text = text.replaceAll("s+", " ");
        // 去掉ASCII控制字符
        text = text.replaceAll("p{Cntrl}", "");
        // 去掉ASCII非打印字符
        text = text.replaceAll("[^p{Print}]", "");
        // 从Unicode去除非打印字符
        text = text.replaceAll("p{C}", "");
        System.out.println(text);
    }

}
