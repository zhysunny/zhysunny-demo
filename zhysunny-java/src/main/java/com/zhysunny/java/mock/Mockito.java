package com.zhysunny.java.mock;

/**
 * @author 章云
 * @date 2019/11/6 9:29
 */
public class Mockito {

    private static String name1 = "Static Mockito";

    private String name2 = "Mockito";

    public String publicToString() {
        return "publicToString";
    }

    public void publicToVoid() {
        System.out.println("publicToVoid");
        return;
    }

    public String publicToString(String param1, Integer param2) {
        return param1 + param2;
    }

}
