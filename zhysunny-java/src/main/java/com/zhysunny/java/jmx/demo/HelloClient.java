package com.zhysunny.java.jmx.demo;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Arrays;

/**
 * @author 章云
 * @date 2019/12/24 9:54
 */
public class HelloClient {

    public static void main(String[] args) throws Exception {
        String jmxURL = "service:jmx:rmi:///jndi/rmi://10.45.144.76:1000/jmxrmi";
        JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
        JMXConnector connector = JMXConnectorFactory.connect(serviceURL);
        MBeanServerConnection mbsc = connector.getMBeanServerConnection();
        // MBean名称
        ObjectName objectName = new ObjectName("com.zhysunny.java.jmx.demo:name=HelloServer");
        // String
        System.out.println("name:" + mbsc.getAttribute(objectName, "Name"));
        // long
        System.out.println("time:" + mbsc.getAttribute(objectName, "Time"));

        MBeanInfo mBeanInfo = mbsc.getMBeanInfo(objectName);
        String className = mBeanInfo.getClassName();
        System.out.println("MBean类名：" + className);
        String description = mBeanInfo.getDescription();
        System.out.println("描述：" + description);
        MBeanConstructorInfo[] constructors = mBeanInfo.getConstructors();
        System.out.println("构造器个数：" + constructors.length);
        for (MBeanConstructorInfo constructor : constructors) {
            System.out.println(constructor);
        }
        Descriptor descriptor = mBeanInfo.getDescriptor();
        System.out.println("描述信息：" + descriptor);
        MBeanNotificationInfo[] notifications = mBeanInfo.getNotifications();
        System.out.println("通知个数：" + notifications.length);
        for (MBeanNotificationInfo notification : notifications) {
            System.out.println(notification);
        }
        MBeanOperationInfo[] operations = mBeanInfo.getOperations();
        System.out.println("操作个数：" + operations.length);
        for (MBeanOperationInfo operation : operations) {
            System.out.println(operation);
        }
    }

}
