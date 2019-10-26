package com.zhysunny.kafka.main;

import kafka.admin.TopicCommand;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import org.junit.*;

/**
 * KafkaApi Test.
 * @author 章云
 * @date 2019/10/17 21:20
 */
public class KafkaApiTest {

    private static ZkUtils zkUtils;
    private static KafkaApi kafkaApi;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test KafkaApi Class Start...");
        zkUtils = ZkUtils.apply("192.168.1.44:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        kafkaApi = new KafkaApi(zkUtils);
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test KafkaApi Class End...");
    }

    @Test
    public void testGetBrokers() {
        System.out.println(kafkaApi.getBrokers());
    }

    @Test
    public void testMain() {
        TopicCommand.TopicCommandOptions opts = new TopicCommand.TopicCommandOptions(null);
        TopicCommand.createTopic(zkUtils, opts);
    }

}
