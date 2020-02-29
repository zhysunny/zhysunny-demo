package com.zhysunny.java.jmx.process;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * 虚拟机
 * @author 章云
 * @date 2020/2/29 21:20
 */
public class RuntimeMBean {
    public static void main(String[] args) {
        RuntimeMXBean mbean = ManagementFactory.getRuntimeMXBean();
        System.out.println(mbean.getName());
        System.out.println(mbean.getObjectName());
        System.out.println(mbean.getVmName());
        System.out.println(mbean.getVmVendor());
        System.out.println(mbean.getVmVersion());
        System.out.println(mbean.getBootClassPath());
        System.out.println(mbean.getClassPath());
        System.out.println(mbean.getLibraryPath());
        System.out.println(mbean.getInputArguments());
        System.out.println(mbean.getManagementSpecVersion());
        System.out.println(mbean.getSpecName());
        System.out.println(mbean.getSpecVendor());
        System.out.println(mbean.getSpecVersion());
        System.out.println(mbean.getStartTime());
        System.out.println(mbean.getUptime());
        System.out.println(mbean.getSystemProperties());
        System.out.println(mbean.isBootClassPathSupported());
    }
}
