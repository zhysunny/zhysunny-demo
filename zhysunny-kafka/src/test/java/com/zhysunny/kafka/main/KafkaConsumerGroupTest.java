package com.zhysunny.kafka.main;

import com.zhysunny.kafka.constant.FinalConstants;
import kafka.admin.ConsumerGroupCommand;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import org.junit.*;

/**
 * KafkaConsumerGroup Test.
 * @author 章云
 * @date 2019/10/21 16:04
 */
public class KafkaConsumerGroupTest {

    private static ZkUtils zkUtils;
    private static KafkaConsumerGroup kafkaConsumerGroup;
    private ConsumerGroupCommand.ConsumerGroupCommandOptions opts;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test KafkaTopic Class Start...");
        zkUtils = ZkUtils.apply("192.168.1.44:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        kafkaConsumerGroup = new KafkaConsumerGroup(zkUtils);
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test KafkaConsumerGroup Class End...");
    }

    /**
     * Method: listGroups()
     */
    @Test
    public void testListGroups() throws Exception {
        System.out.println(kafkaConsumerGroup.listGroups());
    }

    /**
     * Method: listGroups()
     */
    @Test
    public void testListGroupsForTopic() throws Exception {
        System.out.println(kafkaConsumerGroup.listGroups(FinalConstants.DEFAULT_TOPIC_NAME));
    }

    /**
     * Method: deleteGroups(String groupName)
     */
    @Test
    public void testDeleteGroups() throws Exception {
        kafkaConsumerGroup.deleteGroups("console-consumer-17877");
    }

    /**
     * Method: resetOffset(String groupName)
     */
    @Test
    public void testResetOffset() throws Exception {
        //TODO: Test goes here...
    }

} 
