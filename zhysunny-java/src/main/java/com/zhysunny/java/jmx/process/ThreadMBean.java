package com.zhysunny.java.jmx.process;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * 线程
 * @author 章云
 * @date 2020/2/29 21:26
 */
public class ThreadMBean {
    public static void main(String[] args) {
        ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        System.out.println(mbean.getObjectName());
        System.out.println(mbean.getCurrentThreadCpuTime());
        System.out.println(mbean.getCurrentThreadUserTime());
        System.out.println(mbean.getDaemonThreadCount());
        System.out.println(mbean.getPeakThreadCount());
        System.out.println(mbean.getThreadCount());
        System.out.println(mbean.getTotalStartedThreadCount());
        long[] allThreadIds = mbean.getAllThreadIds();
        for (long id : allThreadIds) {
            System.out.println(mbean.getThreadCpuTime(id));
            System.out.println(mbean.getThreadInfo(id));
            System.out.println(mbean.getThreadUserTime(id));
        }
    }
}
