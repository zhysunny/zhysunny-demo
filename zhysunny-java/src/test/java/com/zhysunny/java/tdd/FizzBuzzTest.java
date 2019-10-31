package com.zhysunny.java.tdd;

import static com.zhysunny.java.tdd.FizzBuzz.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * FizzBuzzTest Test.
 * @author 章云
 * @date 2019/10/31 13:55
 */
public class FizzBuzzTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test FizzBuzzTest Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test FizzBuzzTest Class End...");
    }

    @Test
    public void test() throws Exception {
        assertEquals(getFizzBuzz(1), "1");
        assertEquals(getFizzBuzz(15), "FizzBuzz");
        assertEquals(getFizzBuzz(5), "Buzz");
        assertEquals(getFizzBuzz(3), "Fizz");
    }

}
