package com.zhysunny.java.util.collection.queue;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import java.util.AbstractQueue;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * AbstractQueueTest Test.
 * @author 章云
 * @date 2020/1/8 13:46
 */
public class AbstractQueueTest {
    @Rule
    public ExpectedException expected = ExpectedException.none();

    /**
     * 以ArrayBlockingQueue实现类测试抽象队列
     */
    private AbstractQueue<String> queue = new ArrayBlockingQueue<>(2);

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test AbstractQueueTest Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test AbstractQueueTest Class End...");
    }

    @Test
    public void testAdd() throws Exception {
        // 队列满会抛异常
        // 必定返回true
        queue.add("1");
        queue.add("1");
        expected.expect(IllegalStateException.class);
        expected.expectMessage("Queue full");
        queue.add("1");
    }

    @Test
    public void testRemove() throws Exception {
        // 删除开头元素并返回
        // 开头元素为空抛出异常
        expected.expect(NoSuchElementException.class);
        queue.remove();
    }

}
