package com.zhysunny.java.jmx.demo;

/**
 * @author 章云
 * @date 2019/10/29 15:50
 */
public interface HelloMBean {

    String getName();

    void setName(String name);

    long getTime();

    void printHello();

}
