package com.zhysunny.pattern.behaviour.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字生成器
 * @author 章云
 * @date 2019/6/18 22:41
 */
public abstract class AbstractNumberGenerator {
    private List<PrintObserver> observers = new ArrayList<PrintObserver>();

    public void addObserver(PrintObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PrintObserver observer) {
        observers.remove(observer);
    }

    public void notifyObserver() {
        for (PrintObserver observer : observers) {
            observer.update(this);
        }
    }

    /**
     * 获得当前的数字
     * @return
     */
    public abstract int getNumber();

    /**
     * 执行观察模式
     */
    public abstract void execute();

}
