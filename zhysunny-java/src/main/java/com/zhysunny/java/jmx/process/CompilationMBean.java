package com.zhysunny.java.jmx.process;

import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;

/**
 * 编译
 * @author 章云
 * @date 2020/2/29 21:07
 */
public class CompilationMBean {
    public static void main(String[] args) {
        CompilationMXBean mbean = ManagementFactory.getCompilationMXBean();
        System.out.println(mbean.getName());
        System.out.println(mbean.getObjectName());
        System.out.println(mbean.getTotalCompilationTime());
        System.out.println(mbean.isCompilationTimeMonitoringSupported());
    }
}
