package com.zhysunny.java.jmx.demo;

/**
 * 必须实现接口的属性和方法，才能注册到MBean
 * @author 章云
 * @date 2019/10/29 15:50
 */
public class Hello implements HelloMBean {

    private String name;
    private long time;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getTime() {
        return System.currentTimeMillis();
    }

    @Override
    public void printHello() {
        System.out.println("Hello world, " + name);
    }

    public void printHello(String whoName) {
        System.out.println("Hello, " + whoName);
    }

}
