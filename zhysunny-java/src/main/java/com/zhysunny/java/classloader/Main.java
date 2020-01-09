package com.zhysunny.java.classloader;

/**
 * @author 章云
 * @date 2020/1/9 10:57
 */
public class Main {

    public static void main(String[] args) {
        Thread thread = new PrintThread();
        thread.start();
    }

}
