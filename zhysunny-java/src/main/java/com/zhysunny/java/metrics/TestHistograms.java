package com.zhysunny.java.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Histograms主要使用来统计数据的分布情况，最大值、最小值、平均值、中位数，百分比（75%、90%、95%、98%、99%和99.9%）。 场景：统计API请求响应时间
 * @author 章云
 * @date 2019/6/24 19:03
 */
public class TestHistograms {
    /**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
    private static final MetricRegistry metrics = new MetricRegistry();

    /**
     * 在控制台上打印输出
     */
    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();

    /**
     * 实例化一个Histograms
     */
    private static final Histogram responseTimeMetric = metrics.histogram(MetricRegistry.name(TestHistograms.class, "responsetime"));

    public static void handleRequest(double random) {
        responseTimeMetric.update((int) (random * 100));
    }

    public static void main(String[] args) throws InterruptedException {
        //ConsoleReporter启动 每5秒report输出一次
        reporter.start(5, TimeUnit.SECONDS);
        Random rand = new Random();
        do {
            handleRequest(rand.nextDouble());
            Thread.sleep(100);
        } while (true);
    }
}
