package com.zhysunny.java.tdd;

import static com.zhysunny.java.tdd.FizzBuzz.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * FizzBuzz Test.
 */
public class FizzBuzzTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test FizzBuzz Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test FizzBuzz Class End...");
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getFizzBuzz(int i)
     */
    @Test
    public void testGetFizzBuzz() throws Exception {
        assertEquals(getFizzBuzz(1), "1");
        assertEquals(getFizzBuzz(3), "Fizz");
        assertEquals(getFizzBuzz(5), "Buzz");
        assertEquals(getFizzBuzz(15), "FizzBuzz");
    }

} 
