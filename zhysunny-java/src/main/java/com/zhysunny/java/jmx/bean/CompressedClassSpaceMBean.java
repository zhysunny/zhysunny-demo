package com.zhysunny.java.jmx.bean;

import javax.management.ObjectName;
import java.util.Arrays;

/**
 * 压缩类空间
 * @author 章云
 * @date 2019/12/24 8:51
 */
public class CompressedClassSpaceMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("java.lang:type=MemoryPool,name=Compressed Class Space");
        // String
        System.out.println("MBean名称:" + mbsc.getAttribute(objectName, "Name"));
        // String[]
        System.out.println("内存管理名称:" + Arrays.toString((String[])mbsc.getAttribute(objectName, "MemoryManagerNames")));
        // String
        System.out.println("内存类型:" + mbsc.getAttribute(objectName, "Type"));
        // javax.management.openmbean.CompositeData
        System.out.println("使用峰值:" + mbsc.getAttribute(objectName, "PeakUsage"));
        // javax.management.openmbean.CompositeData
        System.out.println("集合使用:" + mbsc.getAttribute(objectName, "CollectionUsage"));
        // long
//        System.out.println("集合使用阈值:" + mbsc.getAttribute(objectName, "CollectionUsageThreshold"));
        // long
//        System.out.println("集合使用阈值个数:" + mbsc.getAttribute(objectName, "CollectionUsageThresholdCount"));
        // boolean
//        System.out.println("是否超过集合使用阈值:" + mbsc.getAttribute(objectName, "CollectionUsageThresholdExceeded"));
        // boolean
        System.out.println("是否支持集合使用阈值:" + mbsc.getAttribute(objectName, "CollectionUsageThresholdSupported"));
        // javax.management.openmbean.CompositeData
        System.out.println("使用:" + mbsc.getAttribute(objectName, "Usage"));
        // long
        System.out.println("使用阈值:" + mbsc.getAttribute(objectName, "UsageThreshold"));
        // long
        System.out.println("使用阈值个数:" + mbsc.getAttribute(objectName, "UsageThresholdCount"));
        // boolean
        System.out.println("是否超过使用阈值:" + mbsc.getAttribute(objectName, "UsageThresholdExceeded"));
        // boolean
        System.out.println("是否支持使用阈值:" + mbsc.getAttribute(objectName, "UsageThresholdSupported"));
        // boolean
        System.out.println("是否有效:" + mbsc.getAttribute(objectName, "Valid"));
        // 1个操作
        print(objectName);
    }

}
