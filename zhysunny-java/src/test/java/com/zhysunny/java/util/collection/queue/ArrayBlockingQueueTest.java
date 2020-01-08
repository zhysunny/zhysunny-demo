package com.zhysunny.java.util.collection.queue;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.AbstractQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueueTest Test.
 * @author 章云
 * @date 2020/1/8 14:20
 */
public class ArrayBlockingQueueTest {

    /**
     * 初始化一定容量的数组
     * 使用一个重入锁，默认使用非公平锁，入队和出队共用一个锁，互斥
     * 是有界设计，如果容量满无法继续添加元素直至有元素被移除
     * 使用时开辟一段连续的内存，如果初始化容量过大容易造成资源浪费，过小易添加失败
     */
    private ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test ArrayBlockingQueueTest Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test ArrayBlockingQueueTest Class End...");
    }

    @Test
    public void test() throws Exception {
        // 添加成功，返回true
        // 添加失败(队列已满) 抛异常new IllegalStateException("Queue full")
        queue.add("1");
        // 队列未满添加成功不返回
        // 队列已满添加时阻塞
        queue.put("2");
        // 添加成功，返回true
        // 添加失败(队列已满)，返回false
        queue.offer("3");
        // 设置超时时间
        //        queue.offer("4", 5, TimeUnit.SECONDS);
        System.out.println(queue);
        // 获取并移除先入的元素，队列为空抛异常
        System.out.println(queue.remove());
        queue.element();//和remove一样
        // 获取并移除先入的元素，队列为空阻塞
        System.out.println(queue.take());
        // 获取并移除先入的元素，队列为空返回null
        System.out.println(queue.poll());
        // 设置超时时间
        //        queue.poll(5, TimeUnit.SECONDS);
        // 获取不移除先入的元素，队列为空返回null
        System.out.println(queue.peek());
    }

}
