package com.zhysunny.pattern.behaviour.strategy;

/**
 * @author 章云
 * @date 2019/11/28 15:40
 */
public class Main {

    public static void main(String[] args) {
        ICalculator cal = new Plus();
        System.out.println(cal.calculate("2+8"));
        cal = new Minus();
        System.out.println(cal.calculate("2-8"));
        cal = new Multiply();
        System.out.println(cal.calculate("2*8"));
    }

}
