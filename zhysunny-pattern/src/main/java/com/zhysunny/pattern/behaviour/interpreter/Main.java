package com.zhysunny.pattern.behaviour.interpreter;

/**
 * @author 章云
 * @date 2019/11/27 14:48
 */
public class Main {

    public static void main(String[] args) {
        // 计算 9+2-8 的值
        int result = new Minus().interpret((new Context(new Plus().interpret(new Context(9, 2)), 8)));
        System.out.println(result);
    }

}
