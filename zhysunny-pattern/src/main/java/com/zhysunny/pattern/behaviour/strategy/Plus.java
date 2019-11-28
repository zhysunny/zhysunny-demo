package com.zhysunny.pattern.behaviour.strategy;

/**
 * @author 章云
 * @date 2019/11/28 15:34
 */
public class Plus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "\\+");
        return arrayInt[0] + arrayInt[1];
    }

}
