package com.zhysunny.java.lock;

/**
 * @author 章云
 * @date 2020/3/18 9:09
 */
public class SynchronizedCode implements Runnable {

    @Override
    public void run() {
        // 加锁操作
        synchronized (this) {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {
        SynchronizedCode test = new SynchronizedCode();
        Thread thread = new Thread(test);
        thread.start();
    }

}
