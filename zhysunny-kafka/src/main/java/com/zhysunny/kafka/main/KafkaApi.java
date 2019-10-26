package com.zhysunny.kafka.main;

import kafka.cluster.Broker;
import kafka.cluster.EndPoint;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.protocol.SecurityProtocol;
import scala.collection.JavaConversions;
import scala.collection.Seq;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 章云
 * @date 2019/10/17 21:20
 */
public class KafkaApi {

    private ZkUtils zkUtils;

    public KafkaApi(ZkUtils zkUtils) {
        this.zkUtils = zkUtils;
    }

    public Map<Integer, String> getBrokers() {
        Seq<Broker> brokers = zkUtils.getAllBrokersInCluster();
        Map<Integer, String> map = new HashMap<>();
        for (Broker broker : JavaConversions.seqAsJavaList(brokers)) {
            scala.collection.immutable.Map<SecurityProtocol, EndPoint> endPointMap = broker.endPoints();
            EndPoint endPoint = endPointMap.get(SecurityProtocol.PLAINTEXT).get();
            map.put(broker.id(), endPoint.host() + ":" + endPoint.port());
        }
        return map;
    }

}
