package com.zhysunny.java.structure.sort;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;

/**
 * BubbleSort Test.
 * @author 章云
 * @date 2019/11/23 15:10
 */
public class BubbleSortTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test BubbleSort Class Start...");
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
    public void testSort1() throws Exception {
        int[] array = { 3 };
        int[] sort = new BubbleSort(array).getSort();
        test(array, sort);
    }

    @Test
    public void testSort2() throws Exception {
        int[] array = { 3, 2 };
        int[] sort = new BubbleSort(array).getSort();
        test(array, sort);
    }

    @Test
    public void testSort3() throws Exception {
        int[] array = { 3, 2, 1, 6, 2 };
        int[] sort = new BubbleSort(array).getSort();
        test(array, sort);
    }

    @Test
    public void testSort4() throws Exception {
        int[] array = { 10, 8, 12, 6, 15, 7 };
        int[] sort = new BubbleSort(array).getSort();
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
