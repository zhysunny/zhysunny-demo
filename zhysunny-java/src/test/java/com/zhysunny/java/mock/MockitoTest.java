package com.zhysunny.java.mock;

import org.junit.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Mockito Test.
 */
public class MockitoTest {

    private Mockito mockitoMock;
    private Mockito mockitoSpy;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test Mockito Class Start...");
    }

    @Before
    public void before() throws Exception {
        // mock是虚拟对象，调用方法不会获得期望值
        mockitoMock = mock(Mockito.class);
        // spy是真实对象，如果不设置规则返回真实值
        mockitoSpy = spy(Mockito.class);
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test Mockito Class End...");
    }

    /**
     * Method: publicToString()
     */
    @Test
    public void testPublicToString() throws Exception {
        System.out.println(mockitoMock.publicToString()); //null
        // 给mock对象设置期望值
        when(mockitoMock.publicToString()).thenReturn("mock publicToString");
        System.out.println(mockitoMock.publicToString());
        // spy对象也可以设置期望值
        // 注：每次调用都会执行真实方法并返回期望结果
        when(mockitoSpy.publicToString()).thenReturn("spy publicToString");
        System.out.println(mockitoSpy.publicToString());
        // 注：每次调用只返回期望结果不执行真实方法
        doReturn("spy publicToString 2").when(mockitoSpy).publicToString();
        System.out.println(mockitoSpy.publicToString());
        // 参数匹配
        // any()
        // anyInt()
        // anyString()
        // anyBoolean()
        // anyList()
        // anyMap()
        // eq()
        when(mockitoMock.publicToString(anyString(), anyInt())).thenReturn("publicToString and param");
        System.out.println(mockitoMock.publicToString("aaa", 4));
    }

    /**
     * Method: publicToVoid()
     */
    @Test
    public void testPublicToVoid() throws Exception {
        // 什么也不做(针对返回值为void的方法调用)
        doNothing().when(mockitoMock).publicToVoid();
        // 模拟异常
//        doThrow(RuntimeException.class).when(mockitoMock).publicToVoid();
        // 调用mock对象的真实方法(对虚拟对象无效)
        when(mockitoMock.publicToString()).thenCallRealMethod();
        mockitoMock.publicToVoid();
        mockitoSpy.publicToVoid();
//        验证是否被调用1次
//        verify(Mock对象).函数(参数);
//        verify(Mock对象,times(1)).函数(参数);
//        验证是否被调用n次
//        verify(Mock对象,times(n)).函数(参数);
//        验证是否从未被调用过
//        verify(Mock对象,never()).函数(参数);
//        验证至少调用1次
//        verify(Mock对象,atLeastOnce()).函数(参数);
//        验证至少调用n次
//        verify(Mock对象,atLeast(n)).函数(参数);
//        验证至多调用n次
//        verify(Mock对象,atMost(n)).函数(参数);
    }

} 
