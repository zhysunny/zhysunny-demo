package com.zhysunny.pattern.behaviour.templateMethod;

/**
 * @author 章云
 * @date 2019/11/28 15:45
 */
public class Main {

    public static void main(String[] args) {
        AbstractCalculator cal = new Plus();
        System.out.println(cal.calculate("8+8", "\\+"));
        cal = new Minus();
        System.out.println(cal.calculate("8-2", "-"));
        cal = new Multiply();
        System.out.println(cal.calculate("8*8", "\\*"));
    }

}
