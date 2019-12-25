package com.zhysunny.java.jmx.bean;

import com.zhysunny.common.date.DateUtils;
import javax.management.ObjectName;
import java.util.Arrays;

/**
 * 运行时
 * @author 章云
 * @date 2019/12/24 9:01
 */
public class RuntimeMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("java.lang:type=Runtime");
        // String
        System.out.println("名称:" + mbsc.getAttribute(objectName, "Name"));
        // String
        System.out.println("VM名称:" + mbsc.getAttribute(objectName, "VmName"));
        // String
        System.out.println("VM版本:" + mbsc.getAttribute(objectName, "VmVersion"));
        // String
        System.out.println("VM供应商:" + mbsc.getAttribute(objectName, "VmVendor"));
        // long
        System.out.println("运行时间:" + mbsc.getAttribute(objectName, "Uptime"));
        // long
        System.out.println("开始时间:" + DateUtils.getStringOfLong((long)mbsc.getAttribute(objectName, "StartTime"), "yyyy-MM-dd HH:mm:ss"));
        // String
        System.out.println("规范名称:" + mbsc.getAttribute(objectName, "SpecName"));
        // String
        System.out.println("规范版本:" + mbsc.getAttribute(objectName, "SpecVersion"));
        // String
        System.out.println("规范供应商:" + mbsc.getAttribute(objectName, "SpecVendor"));
        // String
        System.out.println("管理规范版本:" + mbsc.getAttribute(objectName, "ManagementSpecVersion"));
        // javax.management.openmbean.TabularData
        System.out.println("系统属性:" + mbsc.getAttribute(objectName, "SystemProperties"));
        // String
        System.out.println("库路径:" + mbsc.getAttribute(objectName, "LibraryPath"));
        // String
        System.out.println("类路径:" + mbsc.getAttribute(objectName, "ClassPath"));
        // String[]
        System.out.println("VM 参数:" + Arrays.toString((String[])mbsc.getAttribute(objectName, "InputArguments")));
        // boolean
        System.out.println("是否支持引导类路径:" + mbsc.getAttribute(objectName, "BootClassPathSupported"));
        // String
        System.out.println("引导类路径:" + mbsc.getAttribute(objectName, "BootClassPath"));
        // 无
        print(objectName);
        // 计算CPU
        //        Math.min(100.00F, (ProcessCpuTime-PreProcessCpuTime) / ((Uptime-PreUptime) * 10000F * AvailableProcessors));
    }

}
