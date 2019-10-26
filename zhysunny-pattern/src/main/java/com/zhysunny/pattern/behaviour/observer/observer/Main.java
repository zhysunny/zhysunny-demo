package com.zhysunny.pattern.behaviour.observer.observer;

import com.zhysunny.pattern.behaviour.observer.AbstractNumberGenerator;

/**
 * 观察者模式测试类
 * @author 章云
 * @date 2019/6/18 22:45
 */
public class Main {
    public static void main(String[] args) {
        AbstractNumberGenerator generator = new RandomNumberGenerator();
        generator.addObserver(new DigitObserver());
        generator.addObserver(new GraphObserver());
        generator.execute();
    }
}
