package com.zhysunny.java.util.concurrent.locks.impl;

import com.zhysunny.java.util.concurrent.locks.MapLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟读写线程
 * @author 章云
 * @date 2019/12/20 14:06
 */
public class ReentrantLockImpl implements MapLock {

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public String get(String key) {
        lock.lock();
        try {
            return MAP.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void put(String key, String value) {
        lock.lock();
        try {
            MAP.put(key, value);
        } finally {
            lock.unlock();
        }
    }

}
