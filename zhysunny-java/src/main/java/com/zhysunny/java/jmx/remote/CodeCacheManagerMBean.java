package com.zhysunny.java.jmx.remote;

import javax.management.ObjectName;
import java.util.Arrays;

/**
 * 代码缓存管理器
 * @author 章云
 * @date 2019/12/23 18:07
 */
public class CodeCacheManagerMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("java.lang:type=MemoryManager,name=CodeCacheManager");
        // String
        System.out.println("MBean名称:" + mbsc.getAttribute(objectName, "Name"));
        // String[]
        System.out.println("内存名列表:" + Arrays.toString((String[])mbsc.getAttribute(objectName, "MemoryPoolNames")));
        // boolean
        System.out.println("是否有效:" + mbsc.getAttribute(objectName, "Valid"));
        // 无
        print(objectName);
    }

}
