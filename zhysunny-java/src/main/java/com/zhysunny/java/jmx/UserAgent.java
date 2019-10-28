package com.zhysunny.java.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author 章云
 * @date 2019/10/28 11:18
 */
public class UserAgent implements NotificationListener {

    private MBeanServer mBeanServer;
    private MBeanServer mBeanServer1;

    public UserAgent() {
        mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer1 = MBeanServerFactory.createMBeanServer("Hello");

        try {
            ObjectName name = new ObjectName("UserAgent:type=User1");

            User user = new User();
            user.setName("test");
            user.setId(1);
            user.setBirthDate(new Date());
            user.setTime(LocalTime.now());
            Test test = new Test();
            test.setName("mytest");
            test.setAge(11);
            user.setTest(test);
            mBeanServer.registerMBean(user, name);

            mBeanServer1.registerMBean(user, new ObjectName("Hello:type=user"));

            Thread.sleep(Long.MAX_VALUE);

        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {

    }

}
