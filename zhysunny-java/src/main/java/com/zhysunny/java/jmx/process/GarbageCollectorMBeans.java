package com.zhysunny.java.jmx.process;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.util.Arrays;
import java.util.List;

/**
 * GC
 * @author 章云
 * @date 2020/2/29 21:10
 */
public class GarbageCollectorMBeans {

    public static void main(String[] args) {
        List<GarbageCollectorMXBean> mbeans = ManagementFactory.getGarbageCollectorMXBeans();
        // java.lang:type=GarbageCollector,name=PS Scavenge
        // java.lang:type=GarbageCollector,name=PS MarkSweep
        System.out.println(mbeans.size());
        for (GarbageCollectorMXBean mbean : mbeans) {
            System.out.println("===========================");
            System.out.println(mbean.getClass());
            System.out.println(mbean.getName());
            System.out.println(mbean.getObjectName());
            System.out.println(mbean.isValid());
            System.out.println(mbean.getCollectionCount());
            System.out.println(mbean.getCollectionTime());
            System.out.println(Arrays.toString(mbean.getMemoryPoolNames()));
        }
    }

}
