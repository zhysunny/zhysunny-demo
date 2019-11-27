package com.zhysunny.pattern.behaviour.interpreter;

/**
 * @author 章云
 * @date 2019/11/27 14:48
 */
public class Minus implements Expression {

    @Override
    public int interpret(Context context) {
        return context.getNum1() - context.getNum2();
    }

}
