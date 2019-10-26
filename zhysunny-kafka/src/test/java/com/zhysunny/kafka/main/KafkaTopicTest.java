package com.zhysunny.kafka.main;

import com.zhysunny.kafka.constant.FinalConstants;
import kafka.admin.TopicCommand;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import org.junit.*;
import java.util.List;

/**
 * KafkaTopic Test.
 * @author 章云
 * @date 2019/10/18 10:07
 */
public class KafkaTopicTest {

    private static ZkUtils zkUtils;
    private static KafkaTopic kafkaTopic;
    private TopicCommand.TopicCommandOptions opts;
    private String topic;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test KafkaTopic Class Start...");
        zkUtils = ZkUtils.apply("192.168.1.44:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        kafkaTopic = new KafkaTopic(zkUtils);
    }

    @Before
    public void before() throws Exception {
        topic = FinalConstants.DEFAULT_TOPIC_NAME;
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test KafkaTopic Class End...");
    }

    @Test
    public void testMain() throws Exception {
        if (!kafkaTopic.existTopic(topic)) {
            testCreateTopic();
        }
        testAlertTopic();
        testListTopic();
        testDescribeTopic();
        testDeleteTopic();
    }

    /**
     * Method: listTopic()
     */
    @Test
    public void testListTopic() throws Exception {
        System.out.println("----------- list -------------");
        List<String> topics = kafkaTopic.listTopic();
        System.out.println(topics);
    }

    /**
     * Method: existTopic()
     */
    @Test
    public void testExistTopic() throws Exception {
        System.out.println("----------- exist -------------");
        boolean exist = kafkaTopic.existTopic(topic);
        System.out.println(exist);
    }

    /**
     * Method: createTopic(String topicName, int partition, int replication)
     */
    @Test
    public void testCreateTopic() throws Exception {
        System.out.println("----------- create -------------");
        kafkaTopic.createTopic(topic, 3, 1);
    }

    /**
     * Method: alertTopic(String topicName, int partitions)
     */
    @Test
    public void testAlertTopic() throws Exception {
        System.out.println("----------- alert -------------");
        kafkaTopic.alertTopic(topic, 6);
    }

    /**
     * Method: describeTopic(String topicName)
     */
    @Test
    public void testDescribeTopic() throws Exception {
        System.out.println("----------- describe -------------");
        kafkaTopic.describeTopic(topic);
    }

    /**
     * Method: deleteTopic(String topicName)
     */
    @Test
    public void testDeleteTopic() throws Exception {
        System.out.println("----------- delete -------------");
        kafkaTopic.deleteTopic(topic);
    }

} 
