package com.zhysunny.java.util.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 章云
 * @date 2020/1/6 14:39
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        ExecutorService executor;
        // 可缓存线程池，适合执行大量的耗时较少的任务
        // 核心线程数为0，
        // 最大线程数是Integer.MAX_VALUE，
        // 线程空闲60秒回收，
        // SynchronousQueue 默认非公平同步队列
        // Executors.defaultThreadFactory() 默认的线程池工厂
        executor = Executors.newCachedThreadPool();
        executor.shutdown();
        executor = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
        executor.shutdown();
        // 定长线程池，更加快速底相应外界的请求
        // 参数设置核心线程数和最大线程数，
        // 线程空闲不回收，只有核心线程
        // LinkedBlockingQueue 默认链表阻塞队列
        // Executors.defaultThreadFactory() 默认的线程池工厂
        executor = Executors.newFixedThreadPool(1);
        executor.shutdown();
        executor = Executors.newFixedThreadPool(1, Executors.defaultThreadFactory());
        executor.shutdown();
        // 调度线程池，主要用于执行定时任务和具有固定周期的重复任务
        // 参数设置核心线程数和最大线程数，
        // 线程空闲不回收，只有核心线程
        // DelayedWorkQueue
        // Executors.defaultThreadFactory() 默认的线程池工厂
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // 延迟2秒执行
        scheduledExecutorService.schedule(new Task(), 2, TimeUnit.SECONDS);
        // 延迟2秒执行，每隔3秒持续执行
        scheduledExecutorService.scheduleAtFixedRate(new Task(), 2, 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        executor = Executors.newScheduledThreadPool(1, Executors.defaultThreadFactory());
        executor.shutdown();
        // 单线程，不需要处理线程同步的问题
        // 核心线程数和最大线程数为1，
        // 线程空闲不回收，只有核心线程
        // LinkedBlockingQueue 默认链表阻塞队列
        // Executors.defaultThreadFactory() 默认的线程池工厂
        executor = Executors.newSingleThreadExecutor();
        executor.shutdown();
        executor = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
        executor.shutdown();
        // 其他线程池
        // 单线程池和调度线程池结合，适合做定时任务
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.shutdown();
        // 工作窃取线程池
        executor = Executors.newWorkStealingPool();
        executor.shutdown();
        // 享有特权的线程池工厂，继承默认线程池工厂
        Executors.privilegedThreadFactory();
    }

}

class Task extends Thread {

    @Override
    public void run() {
        System.out.println("线程测试");
    }

}
