package com.zhysunny.java.metrics;

import com.codahale.metrics.*;
import org.slf4j.LoggerFactory;
import javax.management.ObjectName;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 以Counter为例测试各种Reporter
 * @author 章云
 * @date 2019/10/28 14:26
 */
public class TestReporter {

    private static final MetricRegistry metrics = new MetricRegistry();

    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();

    private static Counter pendingJobs = metrics.counter(MetricRegistry.name(TestCounter.class, "pedding.jobs"));

    private static Queue<String> queue = new LinkedBlockingQueue<String>();

    public static void add(String str) {
        pendingJobs.inc();
        queue.offer(str);
    }

    public static String take() {
        pendingJobs.dec();
        return queue.poll();
    }

    public static void main(String[] args) throws Exception {
        //        consoleReporter();
        //        slf4jReporter();
        jmxReporter();
    }

    private static void consoleReporter() throws InterruptedException {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();
        reporter.start(3, TimeUnit.SECONDS);
        while (true) {
            add("1");
            Thread.sleep(1000);
        }
    }

    private static void slf4jReporter() throws InterruptedException {
        Slf4jReporter reporter = Slf4jReporter.forRegistry(metrics).outputTo(LoggerFactory.getLogger(TestReporter.class)).build();
        reporter.start(3, TimeUnit.SECONDS);
        while (true) {
            add("1");
            Thread.sleep(1000);
        }
    }

    private static void jmxReporter() throws Exception {
        JmxReporter reporter = JmxReporter.forRegistry(metrics).build();
        reporter.start();
        while (true) {
            add("1");
            Thread.sleep(1000);
            System.out.println(metrics);
        }
    }

}

class MBean implements JmxReporter.JmxCounterMBean {

    @Override
    public long getCount() {
        return 0;
    }

    @Override
    public ObjectName objectName() {
        return null;
    }

}
