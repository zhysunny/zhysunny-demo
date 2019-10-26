package com.zhysunny.pattern.behaviour.observer.listener;

import com.zhysunny.pattern.behaviour.observer.MapObserver;

import java.util.HashMap;

/**
 * 针对map键值对观察监听
 * @author 章云
 * @date 2019/6/18 22:14
 */
public class ListenerMap extends HashMap<String, String> {
    private MapObserver insertObserver = new InsertObserver();
    private MapObserver updateObserver = new UpdateObserver();
    private MapObserver deleteObserver = new DeleteObserver();

    @Override
    public String put(String key, String value) {
        if (this.containsKey(key)) {
            updateObserver.update(key, value);
        } else {
            insertObserver.update(key, value);
        }
        return super.put(key, value);
    }

    @Override
    public String remove(Object key) {
        if (key != null && this.containsKey(key)) {
            deleteObserver.update(key.toString(), null);
            return super.remove(key);
        }
        return null;
    }
}
