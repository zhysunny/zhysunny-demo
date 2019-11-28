package com.zhysunny.pattern.behaviour.memento;

/**
 * 原始类
 * @author 章云
 * @date 2019/11/28 14:52
 */
public class Source {

    private String value;

    public Source(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Memento createMemento() {
        return new Memento(value);
    }

    public void restoreMemento(Memento memento) {
        this.value = memento.getValue();
    }

}
