package com.zhysunny.pattern.behaviour.strategy;

/**
 * @author 章云
 * @date 2019/11/28 15:27
 */
public abstract class AbstractCalculator {

    public int[] split(String exp, String opt) {
        String array[] = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }

}
