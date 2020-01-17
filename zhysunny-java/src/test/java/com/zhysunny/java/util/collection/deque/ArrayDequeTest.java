package com.zhysunny.java.util.collection.deque;

import org.junit.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ArrayDequeTest Test.
 * @author 章云
 * @date 2020/1/17 9:17
 */
public class ArrayDequeTest {

    /**
     * 相对于queue，没有阻塞方法，put() 和 take()
     * queue的接口实现添加都是last，移除都是first
     * deque增加了可以向队头添加元素，队尾移除元素
     */
    private ArrayDeque<String> deque = new ArrayDeque<>(5);

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test ArrayDequeTest Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test ArrayDequeTest Class End...");
    }

    @Test
    public void test() throws Exception {
        // 添加成功，返回true
        // 添加失败(队列已满) 抛异常new IllegalStateException("Queue full")
        deque.add("1");
        deque.addFirst("0");
        // 添加成功，返回true
        // 添加失败(队列已满)，返回false
        deque.offer("3");
        deque.offerFirst("2");
        // 设置超时时间
        //        deque.offer("4", 5, TimeUnit.SECONDS);
        System.out.println(deque);
        // 获取并移除先入的元素，队列为空抛异常
        System.out.println(deque.remove());
        deque.element();//和remove一样
        // 获取并移除先入的元素，队列为空返回null
        System.out.println(deque.poll());
        // 设置超时时间
        //        deque.poll(5, TimeUnit.SECONDS);
        // 获取不移除先入的元素，队列为空返回null
        System.out.println(deque.peek());
    }

}
