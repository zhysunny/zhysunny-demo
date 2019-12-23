package com.zhysunny.java.jmx.bean;

import javax.management.ObjectName;
import java.util.Arrays;

/**
 * 内存
 * @author 章云
 * @date 2019/12/23 19:24
 */
public class MemoryMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("java.lang:type=Memory");
        // String
        System.out.println("是否详细:" + mbsc.getAttribute(objectName, "Verbose"));
        // int
        System.out.println("对象挂起终结计数:" + mbsc.getAttribute(objectName, "ObjectPendingFinalizationCount"));
        // javax.management.openmbean.CompositeData
        System.out.println("非堆内存使用:" + mbsc.getAttribute(objectName, "NonHeapMemoryUsage"));
        // javax.management.openmbean.CompositeData
        System.out.println("堆内存使用:" + mbsc.getAttribute(objectName, "HeapMemoryUsage"));
        // 1个通知
        // 1个操作
        print(objectName);
    }

}
