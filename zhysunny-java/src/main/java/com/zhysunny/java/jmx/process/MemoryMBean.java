package com.zhysunny.java.jmx.process;

import com.zhysunny.common.util.UnitUtils;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * 内存
 * @author 章云
 * @date 2019/12/24 8:51
 */
public class MemoryMBean {

    public static void main(String[] args) {
        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        System.out.println("堆内存");
        print(mbean.getHeapMemoryUsage());
        System.out.println("非堆内存");
        print(mbean.getNonHeapMemoryUsage());
        System.out.println(mbean.getObjectPendingFinalizationCount());
        System.out.println(mbean.isVerbose());
        System.out.println(mbean.getObjectName());
    }

    public static void print(MemoryUsage memoryUsage) {
        if (memoryUsage != null) {
            System.out.println("初始化内存：" + UnitUtils.getCapacityUnit(memoryUsage.getInit()));
            System.out.println("已使用内存：" + UnitUtils.getCapacityUnit(memoryUsage.getUsed()));
            System.out.println("已申请内存：" + UnitUtils.getCapacityUnit(memoryUsage.getCommitted()));
            System.out.println("最大内存：" + UnitUtils.getCapacityUnit(memoryUsage.getMax()));
        }
    }

}
