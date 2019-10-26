package com.zhysunny.pattern.behaviour.observer;

/**
 * 打印观察者模式接口
 * @author 章云
 * @date 2019/6/18 21:34
 */
public interface PrintObserver {
    /**
     * 观察者响应的接口
     * @param generator
     */
    void update(AbstractNumberGenerator generator);

}
