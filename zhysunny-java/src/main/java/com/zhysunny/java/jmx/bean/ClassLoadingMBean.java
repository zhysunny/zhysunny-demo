package com.zhysunny.java.jmx.bean;

import javax.management.ObjectName;

/**
 * 类加载
 * @author 章云
 * @date 2019/12/23 18:48
 */
public class ClassLoadingMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("java.lang:type=ClassLoading");
        // boolean
        System.out.println("是否详细:" + mbsc.getAttribute(objectName, "Verbose"));
        // long
        System.out.println("未加载类个数:" + mbsc.getAttribute(objectName, "UnloadedClassCount"));
        // long
        System.out.println("共加载类个数:" + mbsc.getAttribute(objectName, "TotalLoadedClassCount"));
        // int
        System.out.println("已加载类个数:" + mbsc.getAttribute(objectName, "LoadedClassCount"));
        // 无
        print(objectName);
    }

}
