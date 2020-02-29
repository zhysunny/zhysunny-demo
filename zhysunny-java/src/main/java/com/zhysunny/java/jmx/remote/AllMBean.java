package com.zhysunny.java.jmx.remote;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import java.util.Iterator;
import java.util.Set;

/**
 * 所有MBean
 * @author 章云
 * @date 2019/12/23 17:34
 */
public class AllMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {

        // 所有的ObjectInstance
        Set<ObjectInstance> mbeanSet = mbsc.queryMBeans(null, null);
        System.out.println("mbeanSet.size() : " + mbeanSet.size());
        Iterator<ObjectInstance> iterator = mbeanSet.iterator();
        while (iterator.hasNext()) {
            System.out.println("=================================================");
            ObjectInstance objectInstance = iterator.next();
            ObjectName objectName = objectInstance.getObjectName();
            System.out.println("getKeyPropertyList : " + objectName.getKeyPropertyList());
            System.out.println("canonicalName : " + objectName.getCanonicalName());
            System.out.println("className : " + objectInstance.getClassName());
            MBeanInfo mBeanInfo = mbsc.getMBeanInfo(objectName);
            MBeanAttributeInfo[] attributes = mBeanInfo.getAttributes();
            for (MBeanAttributeInfo attribute : attributes) {
                System.out.println(attribute.getName() + "=" + attribute.getType());
            }
        }
    }

}
