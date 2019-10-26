package com.zhysunny.pattern.create.singleton;

/**
 * 饿汉式(静态常量)
 * @author 章云
 * @date 2019/5/10 14:23
 */
public class Singleton1 {
    /**
     * 静态常量
     */
    private static final Singleton1 INSTANCE1 = new Singleton1();
    /**
     * 静态代码块
     */
    private static Singleton1 INSTANCE2;

    static {
        INSTANCE2 = new Singleton1();
    }

    private Singleton1() {
    }

    public static Singleton1 getInstance1() {
        //静态常量
        return INSTANCE1;
    }

    public static Singleton1 getInstance2() {
        //静态代码块
        return INSTANCE2;
    }
}
