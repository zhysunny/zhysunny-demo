package com.zhysunny.java.jmx.process;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

/**
 * 内存池
 * @author 章云
 * @date 2020/2/29 21:00
 */
public class MemoryPoolMBeans {

    public static void main(String[] args) {
        List<MemoryPoolMXBean> mbeans = ManagementFactory.getMemoryPoolMXBeans();
        // java.lang:type=MemoryPool,name=Code Cache
        // java.lang:type=MemoryPool,name=Metaspace
        // java.lang:type=MemoryPool,name=Compressed Class Space
        // java.lang:type=MemoryPool,name=PS Eden Space
        // java.lang:type=MemoryPool,name=PS Survivor Space
        // java.lang:type=MemoryPool,name=PS Old Gen
        System.out.println(mbeans.size());
        for (MemoryPoolMXBean mbean : mbeans) {
            System.out.println("===========================");
            System.out.println(mbean.getClass());
            System.out.println(mbean.getName());
            System.out.println(mbean.getObjectName());
            System.out.println(mbean.isValid());
            MemoryMBean.print(mbean.getCollectionUsage());
        }
    }

}
