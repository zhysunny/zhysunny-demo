package com.zhysunny.java.base;

/**
 * 字符串在JVM中如何存放
 * @author 章云
 * @date 2019/6/14 9:05
 */
public class StringJvm {
    public static void main(String[] args) {
        String s1 = "hello world";
        String s2 = "hello world";
        String s3 = new String("hello world");
        // 如果常量池中有这个字符串常量，就直接返回，否则将该字符串对象的值存入常量池，再返回。
        String s4 = s3.intern();
        //true
        System.out.println(s1 == s2);
        //false
        System.out.println(s1 == s3);
        //true
        System.out.println(s1 == s4);
    }
}
