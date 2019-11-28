package com.zhysunny.pattern.behaviour.memento;

/**
 * 备忘录类
 * @author 章云
 * @date 2019/11/28 14:53
 */
public class Memento {

    private String value;

    public Memento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
