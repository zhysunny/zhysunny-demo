package com.zhysunny.java.jmx.bean;

import javax.management.*;

/**
 * MBean服务器委托
 * @author 章云
 * @date 2019/12/23 18:26
 */
public class MBeanServerDelegateMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("JMImplementation:type=MBeanServerDelegate");
        // String
        System.out.println("jmx名称:" + mbsc.getAttribute(objectName, "ImplementationName"));
        // String
        System.out.println("jmx供应商:" + mbsc.getAttribute(objectName, "ImplementationVendor"));
        // String
        System.out.println("jmx版本:" + mbsc.getAttribute(objectName, "ImplementationVersion"));
        // String
        System.out.println("MBean服务id:" + mbsc.getAttribute(objectName, "MBeanServerId"));
        // String
        System.out.println("规范名称:" + mbsc.getAttribute(objectName, "SpecificationName"));
        // String
        System.out.println("规范供应商:" + mbsc.getAttribute(objectName, "SpecificationVendor"));
        // String
        System.out.println("规范版本:" + mbsc.getAttribute(objectName, "SpecificationVersion"));
        // 1个通知
        print(objectName);
    }

}
