package com.zhysunny.pattern.behaviour.observer;

/**
 * 针对map的观察者模式接口
 * @author 章云
 * @date 2019/6/18 21:34
 */
public interface MapObserver {
    /**
     * 观察者响应的接口
     * @param key
     * @param value
     */
    void update(String key, String value);

}
