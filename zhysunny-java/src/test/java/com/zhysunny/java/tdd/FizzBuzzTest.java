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
    public void testNumber() throws Exception {
        assertEquals(new FizzBuzz(1).toString(), "1");
        assertEquals(new FizzBuzz(4).toString(), "4");
    }

    @Test
    public void testFizz() throws Exception {
        assertEquals(new FizzBuzz(3).toString(), "Fizz");
        assertEquals(new FizzBuzz(12).toString(), "Fizz");
    }

    @Test
    public void testBuzz() throws Exception {
        assertEquals(new FizzBuzz(5).toString(), "Buzz");
        assertEquals(new FizzBuzz(25).toString(), "Buzz");
    }

    @Test
    public void testFizzBuzz() throws Exception {
        assertEquals(new FizzBuzz(15).toString(), "FizzBuzz");
        assertEquals(new FizzBuzz(45).toString(), "FizzBuzz");
    }

}
