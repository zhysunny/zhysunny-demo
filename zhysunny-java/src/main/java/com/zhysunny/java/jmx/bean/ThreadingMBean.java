package com.zhysunny.java.jmx.bean;

import javax.management.ObjectName;
import java.util.Arrays;

/**
 * 线程
 * @author 章云
 * @date 2019/12/24 9:01
 */
public class ThreadingMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("java.lang:type=Threading");
        // long
        System.out.println("启动线程总数:" + mbsc.getAttribute(objectName, "TotalStartedThreadCount"));
        // boolean
        System.out.println("是否支持线程Cpu时间:" + mbsc.getAttribute(objectName, "ThreadCpuTimeSupported"));
        // boolean
        System.out.println("是否启用线程Cpu时间:" + mbsc.getAttribute(objectName, "ThreadCpuTimeEnabled"));
        // int
        System.out.println("线程数:" + mbsc.getAttribute(objectName, "ThreadCount"));
        // boolean
        System.out.println("是否支持线程争用监控:" + mbsc.getAttribute(objectName, "ThreadContentionMonitoringSupported"));
        // boolean
        System.out.println("是否启用线程争用监控:" + mbsc.getAttribute(objectName, "ThreadContentionMonitoringEnabled"));
        // boolean
        System.out.println("是否支持线程分配内存:" + mbsc.getAttribute(objectName, "ThreadAllocatedMemorySupported"));
        // boolean
        System.out.println("是否启用线程分配内存:" + mbsc.getAttribute(objectName, "ThreadAllocatedMemoryEnabled"));
        // boolean
        System.out.println("是否支持同步器使用:" + mbsc.getAttribute(objectName, "SynchronizerUsageSupported"));
        // int
        System.out.println("峰线程数量:" + mbsc.getAttribute(objectName, "PeakThreadCount"));
        // boolean
        System.out.println("是否支持对象监视器的使用:" + mbsc.getAttribute(objectName, "ObjectMonitorUsageSupported"));
        // int
        System.out.println("守护线程数:" + mbsc.getAttribute(objectName, "DaemonThreadCount"));
        // long
        System.out.println("当前线程用户时间:" + mbsc.getAttribute(objectName, "CurrentThreadUserTime"));
        // long
        System.out.println("当前线程CPU时间:" + mbsc.getAttribute(objectName, "CurrentThreadCpuTime"));
        // boolean
        System.out.println("是否支持当前线程CPU时间:" + mbsc.getAttribute(objectName, "CurrentThreadCpuTimeSupported"));
        // long[]
        System.out.println("所有线程id:" + Arrays.toString((long[])mbsc.getAttribute(objectName, "AllThreadIds")));
        // int
        System.out.println("线程数:" + ((long[])mbsc.getAttribute(objectName, "AllThreadIds")).length);
        // 15个操作
        print(objectName);
    }

}
