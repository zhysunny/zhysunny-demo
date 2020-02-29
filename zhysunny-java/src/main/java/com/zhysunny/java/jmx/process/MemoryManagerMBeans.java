package com.zhysunny.java.jmx.process;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.util.Arrays;
import java.util.List;

/**
 * 内存管理
 * @author 章云
 * @date 2020/2/29 20:54
 */
public class MemoryManagerMBeans {
    public static void main(String[] args) {
        List<MemoryManagerMXBean> mbeans = ManagementFactory.getMemoryManagerMXBeans();
        // java.lang:type=MemoryManager,name=CodeCacheManager
        // java.lang:type=MemoryManager,name=Metaspace Manager
        // java.lang:type=GarbageCollector,name=PS Scavenge
        // java.lang:type=GarbageCollector,name=PS MarkSweep
        System.out.println(mbeans.size());
        for (MemoryManagerMXBean mbean : mbeans) {
            System.out.println("===========================");
            System.out.println(mbean.getClass());
            System.out.println(mbean.getName());
            System.out.println(mbean.getObjectName());
            System.out.println(mbean.isValid());
            System.out.println(Arrays.toString(mbean.getMemoryPoolNames()));
        }
    }
}
