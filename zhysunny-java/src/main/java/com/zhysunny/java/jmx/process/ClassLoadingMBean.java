package com.zhysunny.java.jmx.process;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

/**
 * 类加载
 * @author 章云
 * @date 2020/2/29 20:42
 */
public class ClassLoadingMBean {

    public static void main(String[] args) {
        ClassLoadingMXBean mbean = ManagementFactory.getClassLoadingMXBean();
        System.out.println(mbean.getLoadedClassCount());
        System.out.println(mbean.getTotalLoadedClassCount());
        System.out.println(mbean.getUnloadedClassCount());
        System.out.println(mbean.isVerbose());
        System.out.println(mbean.getObjectName());
    }

}
