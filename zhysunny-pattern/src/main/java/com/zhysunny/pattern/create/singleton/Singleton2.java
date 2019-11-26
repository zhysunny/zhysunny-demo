package com.zhysunny.pattern.create.singleton;

/**
 * 懒汉式
 * @author 章云
 * @date 2019/5/10 14:23
 */
public class Singleton2 {

    private static volatile Singleton2 INSTANCE;

    private Singleton2() {
    }

    public static Singleton2 getInstance1() {
        //线程不安全(不可用)
        if (INSTANCE == null) {
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }

    public static synchronized Singleton2 getInstance2() {
        //线程安全，同步方法(效率低，不推荐用)
        if (INSTANCE == null) {
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }

    public static Singleton2 getInstance3() {
        //线程不安全，同步代码块(不可用)
        if (INSTANCE == null) {
            synchronized (Singleton2.class) {
                INSTANCE = new Singleton2();
            }
        }
        return INSTANCE;
    }

    public static Singleton2 getInstance4() {
        //线程安全，双重检查(线程安全，延迟加载，效率高，推荐用)
        if (INSTANCE == null) {
            synchronized (Singleton2.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton2();
                }
            }
        }
        return INSTANCE;
    }
}
