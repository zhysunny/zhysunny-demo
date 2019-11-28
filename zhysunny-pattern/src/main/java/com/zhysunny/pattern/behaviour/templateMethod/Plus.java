package com.zhysunny.pattern.behaviour.templateMethod;

/**
 * @author 章云
 * @date 2019/11/28 15:34
 */
public class Plus extends AbstractCalculator {

    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }

}
