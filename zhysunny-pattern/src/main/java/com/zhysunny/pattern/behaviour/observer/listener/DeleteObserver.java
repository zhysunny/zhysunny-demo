package com.zhysunny.pattern.behaviour.observer.listener;

import com.zhysunny.pattern.behaviour.observer.MapObserver;

/**
 * 删除key值时触发
 * @author 章云
 * @date 2019/6/18 21:50
 */
public class DeleteObserver implements MapObserver {

    @Override
    public void update(String key, String value) {
        System.out.println("删除键值对：key=" + key);
    }
}
