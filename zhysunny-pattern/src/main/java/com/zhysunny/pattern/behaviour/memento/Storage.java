package com.zhysunny.pattern.behaviour.memento;

/**
 * 存储备忘录的类
 * @author 章云
 * @date 2019/11/28 14:54
 */
public class Storage {

    private Memento memento;

    public Storage(Memento memento) {
        this.memento = memento;
    }

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

}
