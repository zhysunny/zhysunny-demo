package com.zhysunny.pattern.behaviour.observer.listener;

import com.zhysunny.pattern.behaviour.observer.MapObserver;

/**
 * 键值对更新触发
 * @author 章云
 * @date 2019/6/18 21:58
 */
public class UpdateObserver implements MapObserver {

    @Override
    public void update(String key, String value) {
        System.out.println("更新键值对：key=" + key + ",value=" + value);
    }
}
