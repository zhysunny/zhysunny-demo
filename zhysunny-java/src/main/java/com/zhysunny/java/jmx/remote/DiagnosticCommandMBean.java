package com.zhysunny.java.jmx.remote;

import javax.management.ObjectName;

/**
 * 诊断命令
 * @author 章云
 * @date 2019/12/23 18:42
 */
public class DiagnosticCommandMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("com.sun.management:type=DiagnosticCommand");
        // 1个通知
        // 19个操作
        print(objectName);
    }

}
