package com.zhysunny.java.jmx.bean;

import com.zhysunny.common.util.UnitUtils;
import javax.management.ObjectName;
import java.util.Arrays;

/**
 * 日志
 * @author 章云
 * @date 2019/12/24 9:30
 */
public class LoggingMBean extends JmxUrl {

    public static void main(String[] args) throws Exception {
        // MBean名称
        ObjectName objectName = new ObjectName("java.util.logging:type=Logging");
        // String[]
        System.out.println("日志名称:" + Arrays.toString((String[])mbsc.getAttribute(objectName, "LoggerNames")));
        // 3个操作
        print(objectName);
    }

}
