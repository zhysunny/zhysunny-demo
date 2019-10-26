package com.zhysunny.kafka.main;

import kafka.utils.ZkUtils;
import scala.collection.JavaConversions;
import java.util.List;
import java.util.Set;

/**
 * @author 章云
 * @date 2019/10/21 14:09
 */
public class KafkaConsumerGroup {

    private ZkUtils zkUtils;

    public KafkaConsumerGroup(ZkUtils zkUtils) {
        this.zkUtils = zkUtils;
    }

    /**
     * 获取所有消费组
     * @return
     */
    public List<String> listGroups() {
        return JavaConversions.seqAsJavaList(zkUtils.getConsumerGroups());
    }

    /**
     * 获取某个topic的所有消费组
     * @param topicName
     * @return
     */
    public Set<String> listGroups(String topicName) {
        return JavaConversions.setAsJavaSet(zkUtils.getAllConsumerGroupsForTopic(topicName));
    }

    public void deleteGroups(String groupName) {
        System.out.println(zkUtils.getTopicsByConsumerGroup(groupName));
    }

    public void resetOffset(String groupName) {

    }

}
