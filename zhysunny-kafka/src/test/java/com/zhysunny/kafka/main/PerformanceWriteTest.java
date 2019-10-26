package com.zhysunny.kafka.main;

import org.junit.*;
import java.util.List;
import static com.zhysunny.kafka.main.PerformanceWrite.*;
import static org.junit.Assert.*;

/**
 * PerformanceWrite Test.
 * @author 章云
 * @date 2019/10/17 20:23
 */
public class PerformanceWriteTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test PerformanceWrite Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test PerformanceWrite Class End...");
    }

    /**
     * Method: simulationData()
     */
    @Test
    public void testSimulationData() throws Exception {
        List<String> datas = simulationData(1024, 1000);
        assertEquals(datas.size(), 1000);
        assertEquals(datas.get(0).length(), 1024);
        datas.stream().forEach(System.out::println);
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
        //TODO: Test goes here...
    }

} 
