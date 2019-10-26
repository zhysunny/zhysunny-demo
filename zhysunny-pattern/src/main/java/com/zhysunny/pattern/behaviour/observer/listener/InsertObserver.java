package com.zhysunny.pattern.behaviour.observer.listener;

import com.zhysunny.pattern.behaviour.observer.MapObserver;

/**
 * 当增加key值时触发
 * @author 章云
 * @date 2019/6/18 21:51
 */
public class InsertObserver implements MapObserver {
    @Override
    public void update(String key, String value) {
        System.out.println("添加键值对：key=" + key + ",value=" + value);
    }
}
