package com.zhysunny.java.jmx.demo;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author 章云
 * @date 2019/10/29 15:51
 */
public class HelloServer {

    public static void main(String[] args) throws Exception {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        System.setProperty("com.sun.management.jmxremote.port", "1000");
        System.setProperty("com.sun.management.jmxremote.ssl", "false");
        System.setProperty("com.sun.management.jmxremote.authenticate", "false");
        // 下面这种方式不能再JConsole中使用
        //    	MBeanServer server = MBeanServerFactory.createMBeanServer();
        // 首先建立一个MBeanServer,MBeanServer用来管理我们的MBean,通常是通过MBeanServer来获取我们MBean的信息，间接
        // 调用MBean的方法，然后生产我们的资源的一个对象。
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        //为MBean（下面的new Hello()）创建ObjectName实例
        ObjectName helloName = new ObjectName("com.zhysunny.java.jmx.demo:name=HelloServer");
        // 将new Hello()这个对象注册到MBeanServer上去
        Hello hello = new Hello();
        hello.setName("zhysunny");
        server.registerMBean(hello, helloName);

        Thread.sleep(1000000000);

        // 执行参数必须加上
        //        -Djava.rmi.server.hostname=127.0.0.1
        //        -Dcom.sun.management.jmxremote.port=1000
        //        -Dcom.sun.management.jmxremote.ssl=false
        //        -Dcom.sun.management.jmxremote.authenticate=false
        // -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.port=1000 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
    }

}
