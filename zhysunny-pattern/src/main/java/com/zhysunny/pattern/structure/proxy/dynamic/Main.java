package com.zhysunny.pattern.structure.proxy.dynamic;

import com.zhysunny.pattern.structure.proxy.Printable;
import com.zhysunny.pattern.structure.proxy.Printer;

/**
 * 代理模式(动态)测试类
 * @author 章云
 * @date 2019/6/18 21:56
 */
public class Main {
    public static void main(String[] args) {
        LoggerHanlder lh = new LoggerHanlder(new Printer("zhy"));
        Printable printer = (Printable) lh.newProxyInstance();
        printer.print("Hello World");
    }
}
