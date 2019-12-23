package com.zhysunny.java.jmx.bean;

import javax.management.ObjectName;
import java.util.Arrays;

/**
 * 并发标记扫描
 * @author 章云
 * @date 2019/12/23 18:58
 */
public class ConcurrentMarkSweepMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("java.lang:type=GarbageCollector,name=ConcurrentMarkSweep");
        // String
        System.out.println("MBean名称:" + mbsc.getAttribute(objectName, "Name"));
        // String[]
        System.out.println("内存名列表:" + Arrays.toString((String[])mbsc.getAttribute(objectName, "MemoryPoolNames")));
        // javax.management.openmbean.CompositeData
        System.out.println("最后一次GC信息:" + mbsc.getAttribute(objectName, "LastGcInfo"));
        // long
        System.out.println("收集时间:" + mbsc.getAttribute(objectName, "CollectionTime"));
        // long
        System.out.println("收集个数:" + mbsc.getAttribute(objectName, "CollectionCount"));
        // boolean
        System.out.println("是否有效:" + mbsc.getAttribute(objectName, "Valid"));
        // 1个通知
        print(objectName);
    }

}
