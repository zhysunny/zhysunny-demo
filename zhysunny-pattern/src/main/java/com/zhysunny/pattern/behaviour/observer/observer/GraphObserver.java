package com.zhysunny.pattern.behaviour.observer.observer;

import com.zhysunny.pattern.behaviour.observer.AbstractNumberGenerator;
import com.zhysunny.pattern.behaviour.observer.PrintObserver;

/**
 * 观察者(打印num个*)
 * @author 章云
 * @date 2019/6/18 22:35
 */
public class GraphObserver implements PrintObserver {
    @Override
    public void update(AbstractNumberGenerator generator) {
        int num = generator.getNumber();
        StringBuffer sb = new StringBuffer(num);
        for (int i = 0; i < num; i++) {
            sb.append("*");
        }
        System.out.println(sb.toString());
    }
}
