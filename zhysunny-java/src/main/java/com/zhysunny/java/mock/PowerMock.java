package com.zhysunny.java.mock;

/**
 * @author 章云
 * @date 2019/11/6 14:42
 */
public class PowerMock {

    private static String name1 = "Static Mockito";

    private String name2 = "Mockito";

    public static String publicToString() {
        System.out.println("执行publicToString");
        return "publicToString";
    }

    public static final void publicToVoid() {
        System.out.println("publicToVoid");
    }

}
