package com.zhysunny.java.mock;

import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * PowerMock Test.
 */
@RunWith(PowerMockRunner.class) // 告诉JUnit使用PowerMockRunner进行测试
@PrepareForTest({PowerMock.class}) // 所有需要测试的类列在此处，适用于模拟final类或有final, private, static, native方法的类
@PowerMockIgnore("javax.management.*") //为了解决使用powermock后，提示classloader错误
public class PowerMockTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test PowerMock Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test PowerMock Class End...");
    }

    /**
     * Method: publicToString()
     */
    @Test
    public void testPublicToString() throws Exception {
        mockStatic(PowerMock.class);
        when(PowerMock.publicToString()).thenReturn("static test");
        System.out.println(PowerMock.publicToString());
    }

    /**
     * Method: publicToVoid()
     */
    @Test
    public void testPublicToVoid() throws Exception {
        // 虚拟对象无法调用
        mockStatic(PowerMock.class);
        PowerMock.publicToVoid();
    }

}
