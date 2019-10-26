package com.zhysunny.pattern.behaviour.observer.observer;

import com.zhysunny.pattern.behaviour.observer.AbstractNumberGenerator;

import java.util.Random;

/**
 * 随机数生成器
 * @author 章云
 * @date 2019/6/18 22:43
 */
public class RandomNumberGenerator extends AbstractNumberGenerator {
    private static final int COUNT = 10;
    private int num;
    private Random random = new Random();

    @Override
    public int getNumber() {
        return num;
    }

    @Override
    public void execute() {
        for (int i = 0; i < COUNT; i++) {
            num = random.nextInt(20);
            notifyObserver();
        }
    }
}
