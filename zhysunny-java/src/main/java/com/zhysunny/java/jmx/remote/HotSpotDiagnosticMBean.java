package com.zhysunny.java.jmx.remote;

import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import java.util.Arrays;

/**
 * 热点的诊断
 * @author 章云
 * @date 2019/12/23 18:44
 */
public class HotSpotDiagnosticMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("com.sun.management:type=HotSpotDiagnostic");
        // javax.management.openmbean.CompositeData[]
        CompositeData[] compositeData = (CompositeData[])mbsc.getAttribute(objectName, "DiagnosticOptions");
        System.out.println("诊断选项:" + Arrays.toString(compositeData));
        System.out.println("诊断选项个数:" + compositeData.length);
        // 3个操作
        print(objectName);
    }

}
