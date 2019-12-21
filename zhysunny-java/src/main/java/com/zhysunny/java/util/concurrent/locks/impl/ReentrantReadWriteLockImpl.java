package com.zhysunny.java.util.concurrent.locks.impl;

import com.zhysunny.java.util.concurrent.locks.MapLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 章云
 * @date 2019/12/20 14:13
 */
public class ReentrantReadWriteLockImpl implements MapLock {

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock readLock = rwl.readLock();
    private final Lock writeLock = rwl.writeLock();

    @Override
    public String get(String key) {
        readLock.lock();
        try {
            return MAP.get(key);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void put(String key, String value) {
        writeLock.lock();
        try {
            MAP.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

}
