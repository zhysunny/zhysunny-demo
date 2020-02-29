package com.zhysunny.java.jmx.remote;

import com.zhysunny.common.util.UnitUtils;
import javax.management.ObjectName;

/**
 * 操作系统
 * @author 章云
 * @date 2019/12/23 16:35
 */
public class OperatingSystemMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("java.lang:type=OperatingSystem");
        // String
        System.out.println("操作系统名称:" + mbsc.getAttribute(objectName, "Name"));
        // String
        System.out.println("操作系统版本:" + mbsc.getAttribute(objectName, "Version"));
        // String
        System.out.println("操作系统位数:" + mbsc.getAttribute(objectName, "Arch"));
        // long
        System.out.println("总物理内存大小:" + UnitUtils.getCapacityUnit((long)mbsc.getAttribute(objectName, "TotalPhysicalMemorySize")));
        // long
        System.out.println("总交换空间大小:" + UnitUtils.getCapacityUnit((long)mbsc.getAttribute(objectName, "TotalSwapSpaceSize")));
        // long
        System.out.println("可用物理内存大小:" + UnitUtils.getCapacityUnit((long)mbsc.getAttribute(objectName, "FreePhysicalMemorySize")));
        // long
        System.out.println("可用交换空间大小:" + UnitUtils.getCapacityUnit((long)mbsc.getAttribute(objectName, "FreeSwapSpaceSize")));
        // long
        System.out.println("提交的虚拟内存大小:" + UnitUtils.getCapacityUnit((long)mbsc.getAttribute(objectName, "CommittedVirtualMemorySize")));
        // int
        System.out.println("可用处理器:" + mbsc.getAttribute(objectName, "AvailableProcessors"));
        // long
        System.out.println("最大文件打开数:" + mbsc.getAttribute(objectName, "MaxFileDescriptorCount"));
        // long
        System.out.println("打开文件数:" + mbsc.getAttribute(objectName, "OpenFileDescriptorCount"));
        // double
        System.out.println("进程Cpu负载:" + mbsc.getAttribute(objectName, "ProcessCpuLoad"));
        // double
        System.out.println("进程的Cpu时间:" + mbsc.getAttribute(objectName, "ProcessCpuTime"));
        // double
        System.out.println("系统Cpu负载:" + mbsc.getAttribute(objectName, "SystemCpuLoad"));
        // double
        System.out.println("系统平均负载:" + mbsc.getAttribute(objectName, "SystemLoadAverage"));
        // 无
        print(objectName);
    }

}
