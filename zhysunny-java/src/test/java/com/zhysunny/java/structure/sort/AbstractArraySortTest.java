package com.zhysunny.java.structure.sort;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;

/**
 * BubbleSort Test.
 * @author 章云
 * @date 2019/11/23 15:10
 */
public class AbstractArraySortTest {

    private static int[] array;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test BubbleSort Class Start...");
        array = new int[]{ 10, 8, 12, 6, 15, 7,11 };
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test BubbleSort Class End...");
    }

    @Test
    public void testBubbleSort1() throws Exception {
        int[] sort = new BubbleSort1(array).getSorted();
        test(array, sort);
    }

    @Test
    public void testBubbleSort2() throws Exception {
        int[] sort = new BubbleSort2(array).getSorted();
        test(array, sort);
    }

    @Test
    public void testBubbleSort3() throws Exception {
        int[] sort = new BubbleSort3(array).getSorted();
        test(array, sort);
    }

    @Test
    public void testSelectionSort1() throws Exception {
        int[] sort = new SelectionSort1(array).getSorted();
        test(array, sort);
    }

    @Test
    public void testSelectionSort2() throws Exception {
        int[] sort = new SelectionSort2(array).getSorted();
        test(array, sort);
    }

    @Test
    public void testInsertionSort1() throws Exception {
        int[] sort = new InsertionSort1(array).getSorted();
        test(array, sort);
    }

    @Test
    public void testInsertionSort2() throws Exception {
        int[] sort = new InsertionSort2(array).getSorted();
        test(array, sort);
    }

    /**
     * @param array 排序前
     * @param sort  排序后
     */
    private void test(int[] array, int[] sort) {
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], sort[i]);
        }
    }

}
