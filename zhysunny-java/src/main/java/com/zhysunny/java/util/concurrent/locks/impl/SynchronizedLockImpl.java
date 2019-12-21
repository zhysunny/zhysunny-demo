package com.zhysunny.java.util.concurrent.locks.impl;

import com.zhysunny.java.util.concurrent.locks.MapLock;

/**
 * @author 章云
 * @date 2019/12/20 14:10
 */
public class SynchronizedLockImpl implements MapLock {

    @Override
    public String get(String key) {
        synchronized (MAP) {
            return MAP.get(key);
        }
    }

    @Override
    public void put(String key, String value) {
        synchronized (MAP) {
            MAP.put(key, value);
        }
    }

}
