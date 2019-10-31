package com.zhysunny.java.tdd;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 * Ticktacktoe Test.
 * @author 章云
 * @date 2019/10/31 14:42
 */
public class TicktacktoeTest {

    private Ticktacktoe ticktacktoe;
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test Ticktacktoe Class Start...");
    }

    @Before
    public void before() throws Exception {
        ticktacktoe = new Ticktacktoe(Ticktacktoe.PLAY1);
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test Ticktacktoe Class End...");
    }

    @Test
    public void testRuntimeExceptionForX() {
        expected.expect(RuntimeException.class);
        // 必须和异常信息相同
        expected.expectMessage("X is outside board");
        ticktacktoe.play(0, 2);
    }

    @Test
    public void testRuntimeExceptionForY() {
        expected.expect(RuntimeException.class);
        expected.expectMessage("Y is outside board");
        ticktacktoe.play(2, 0);
    }

    @Test
    public void testRuntimeException() {
        ticktacktoe.play(2, 2);
        expected.expect(RuntimeException.class);
        expected.expectMessage("Box is occupied");
        ticktacktoe.play(2, 2);
    }

    @Test
    public void testKeydown() {
        ticktacktoe.play(1, 3);
        assertEquals(ticktacktoe.getChar(1, 3), Ticktacktoe.PLAY1);
        ticktacktoe.play(2, 2);
        assertEquals(ticktacktoe.getChar(2, 2), Ticktacktoe.PLAY2);
    }

    @Test
    public void testWinner() {
        ticktacktoe.play(2, 2);
        ticktacktoe.play(1, 1);
        ticktacktoe.play(3, 1);
        ticktacktoe.play(1, 3);
        ticktacktoe.play(1, 2);
        ticktacktoe.play(3, 2);
        ticktacktoe.play(2, 3);
        ticktacktoe.play(3, 3);
        ticktacktoe.play(2, 1);
    }

}
