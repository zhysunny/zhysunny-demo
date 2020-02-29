package com.zhysunny.java.jmx.remote;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * @author 章云
 * @date 2019/12/23 17:53
 */
public class JmxUrl {

    protected static MBeanServerConnection mbsc;

    static {
        String jmxURL = "service:jmx:rmi:///jndi/rmi://10.45.154.217:9101/jmxrmi";
        try {
            JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
            JMXConnector connector = JMXConnectorFactory.connect(serviceURL);
            mbsc = connector.getMBeanServerConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void print(ObjectName objectName) throws Exception {
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
