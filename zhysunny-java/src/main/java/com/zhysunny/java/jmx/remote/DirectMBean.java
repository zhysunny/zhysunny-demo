package com.zhysunny.java.jmx.remote;

import com.zhysunny.common.util.UnitUtils;
import javax.management.ObjectName;

/**
 * 直接缓存池
 * @author 章云
 * @date 2019/12/24 9:30
 */
public class DirectMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("java.nio:type=BufferPool,name=direct");
        // String
        System.out.println("缓存池名称:" + mbsc.getAttribute(objectName, "Name"));
        // long
        System.out.println("总容量:" + UnitUtils.getCapacityUnit((long)mbsc.getAttribute(objectName, "TotalCapacity")));
        // long
        System.out.println("已使用内存:" + UnitUtils.getCapacityUnit((long)mbsc.getAttribute(objectName, "MemoryUsed")));
        // long
        System.out.println("缓存个数:" + mbsc.getAttribute(objectName, "Count"));
        // 无
        print(objectName);
    }

}
