package com.zhysunny.pattern.create.singleton;

/**
 * 静态内部类(推荐用)
 * @author 章云
 * @date 2019/5/10 14:23
 */
public class Singleton3 {

    private Singleton3() {
    }

    private static class Inner {
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return Inner.INSTANCE;
    }

}
