package com.zhysunny.java.jmx.process;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * 操作系统
 * @author 章云
 * @date 2020/2/29 21:17
 */
public class OperatingSystemMBean {

    public static void main(String[] args) {
        OperatingSystemMXBean mbean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(mbean.getName());
        System.out.println(mbean.getObjectName());
        System.out.println(mbean.getArch());
        System.out.println(mbean.getAvailableProcessors());
        System.out.println(mbean.getSystemLoadAverage());
        System.out.println(mbean.getVersion());
        int availableProcessors = mbean.getAvailableProcessors();
        double systemLoadAverage = mbean.getSystemLoadAverage();
        double cpu = systemLoadAverage / availableProcessors * 100;
        if (cpu >= 0) {
            System.out.println(Math.min(100.00, cpu) + " %");
        } else {
            System.out.println(0.0 + " %");
        }
    }

}
