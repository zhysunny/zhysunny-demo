package com.zhysunny.java.jmx.bean;

import javax.management.ObjectName;

/**
 * 编译
 * @author 章云
 * @date 2019/12/23 18:51
 */
public class CompilationMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("java.lang:type=Compilation");
        // String
        System.out.println("编译器名称:" + mbsc.getAttribute(objectName, "Name"));
        // long
        System.out.println("共编译时间:" + mbsc.getAttribute(objectName, "TotalCompilationTime"));
        // boolean
        System.out.println("是否支持编译时间监控:" + mbsc.getAttribute(objectName, "CompilationTimeMonitoringSupported"));
        // 无
        print(objectName);
    }

}
