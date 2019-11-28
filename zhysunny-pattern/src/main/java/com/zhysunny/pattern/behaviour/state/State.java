package com.zhysunny.pattern.behaviour.state;

/**
 * 状态核心类
 * @author 章云
 * @date 2019/11/28 15:09
 */
public class State {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void method1() {
        System.out.println("execute the first opt!");
    }

    public void method2() {
        System.out.println("execute the second opt!");
    }

}
