package com.zhysunny.java.coding;

import org.junit.*;
import org.junit.rules.ExpectedException;
import java.util.Arrays;
import java.util.Random;
import static org.junit.Assert.*;

/**
 * ByteCoding32Test Test.
 * @author 章云
 * @date 2019/11/1 9:02
 */
public class ByteCoding32Test {

    @Rule
    public ExpectedException expected = ExpectedException.none();
    private static float[] value = new float[256];

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test ByteCoding32Test Class Start...");
        Random random = new Random();
        for (int i = 0; i < value.length; i++) {
            value[i] = random.nextFloat() * 0.5f - 0.25f;
        }
        System.out.println(Arrays.toString(value));
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test ByteCoding32Test Class End...");
    }

    @Test
    public void testEncode() throws Exception {
        byte[] decode = { 1, -5, 7, 17 };
        float[] value = new float[4];
        for (int i = 0; i < decode.length; i++) {
            value[i] = (float)decode[i] / 128.0f;
        }
        System.out.println(Arrays.toString(value));
        byte[] encode = ByteCoding32.encode(value);
        System.out.println(Arrays.toString(encode));
        assertEquals(encode[0], 8);
        assertEquals(encode[1], -77);
        assertEquals(encode[2], -94);
    }

    @Test
    public void testEncodeException() throws Exception {
        float[] value = { 1, 5, 7 };
        expected.expect(RuntimeException.class);
        expected.expectMessage("The array length must be a multiple of 4");
        ByteCoding32.encode(value);
    }

    @Test
    public void testDecode() throws Exception {
        byte[] encode = { 8, -77, -94 };
        float[] value = ByteCoding32.decode(encode);
        byte[] decode = new byte[4];
        for (int i = 0; i < value.length; i++) {
            decode[i] = (byte)Math.round(value[i] * 128.0f);
        }
        System.out.println(Arrays.toString(decode));
        assertEquals(decode[0], 1);
        assertEquals(decode[1], 5);
        assertEquals(decode[2], 7);
        assertEquals(decode[3], 17);
    }

    @Test
    public void testDecodeException() throws Exception {
        byte[] value = { 1, 5, 7, 17 };
        expected.expect(RuntimeException.class);
        expected.expectMessage("The array length must be a multiple of 3");
        ByteCoding32.decode(value);
    }

}
