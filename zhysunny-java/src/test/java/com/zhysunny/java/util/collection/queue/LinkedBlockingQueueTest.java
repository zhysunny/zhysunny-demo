package com.zhysunny.java.util.collection.queue;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueueTest Test.
 * @author 章云
 * @date 2020/1/8 16:20
 */
public class LinkedBlockingQueueTest {

    /**
     * 内部使用节点关联，会产生多一点内存占用
     * 使用两个重入锁分别控制元素的入队和出队，用Condition进行线程间的唤醒和等待
     * 有边界的，在默认构造方法中容量是Integer.MAX_VALUE
     * 非连续性内存空间
     */
    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(2);

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test LinkedBlockingQueueTest Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test LinkedBlockingQueueTest Class End...");
    }

    @Test
    public void test() throws Exception {
        System.out.println("Junit Test");
    }

}
