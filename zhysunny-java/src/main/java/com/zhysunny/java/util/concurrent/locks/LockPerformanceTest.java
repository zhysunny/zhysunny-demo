package com.zhysunny.java.util.concurrent.locks;

import com.zhysunny.java.util.concurrent.locks.impl.ReentrantLockImpl;
import com.zhysunny.java.util.concurrent.locks.impl.ReentrantReadWriteLockImpl;
import com.zhysunny.java.util.concurrent.locks.impl.SynchronizedLockImpl;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 读写锁
 * @author 章云
 * @date 2019/12/20 11:20
 */
public class LockPerformanceTest {

    private static AtomicInteger key;

    public static void main(String[] args) throws InterruptedException {
        MapLock synchronizedLock = new SynchronizedLockImpl();
        MapLock reentrantLock = new ReentrantLockImpl();
        MapLock reentrantReadWriteLock = new ReentrantReadWriteLockImpl();
        int thread = 5;
        int count = 10000;
        start(synchronizedLock, thread, count);
        start(reentrantLock, thread, count);
        start(reentrantReadWriteLock, thread, count);
    }

    private static void start(MapLock mapLock, int thread, int count) throws InterruptedException {
        long start = System.currentTimeMillis();
        key = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(thread);
        for (int i = 0; i < thread; i++) {
            Thread write = new Write(mapLock, countDownLatch, count);
            write.start();
        }
        for (int i = 0; i < thread; i++) {
            Thread read = new Read(mapLock, countDownLatch, count);
            read.start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(mapLock.getClass() + "===" + (end - start));
        System.out.println(mapLock.size());
        mapLock.clear();
    }

    private static class Read extends Thread {

        private int count;
        private MapLock mapLock;
        private CountDownLatch countDownLatch;

        public Read(MapLock mapLock, CountDownLatch countDownLatch, int count) {
            this.mapLock = mapLock;
            this.count = count;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            while (true) {
                mapLock.get(key.toString());
                key.incrementAndGet();
                if (key.get() > count) {
                    break;
                }
            }
            countDownLatch.countDown();
        }

    }

    private static class Write extends Thread {

        private int count;
        private MapLock mapLock;
        private CountDownLatch countDownLatch;

        public Write(MapLock mapLock, CountDownLatch countDownLatch, int count) {
            this.mapLock = mapLock;
            this.count = count;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            while (true) {
                key.incrementAndGet();
                mapLock.put(key.toString(), null);
                if (key.get() > count) {
                    break;
                }
            }
            countDownLatch.countDown();
        }

    }

}
