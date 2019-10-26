package com.zhysunny.pattern.behaviour.observer.observer;

import com.zhysunny.pattern.behaviour.observer.AbstractNumberGenerator;
import com.zhysunny.pattern.behaviour.observer.PrintObserver;

/**
 * 观察者(打印数字)
 * @author 章云
 * @date 2019/6/18 22:34
 */
public class DigitObserver implements PrintObserver {
    @Override
    public void update(AbstractNumberGenerator generator) {
        System.out.println(generator.getNumber());
    }
}
