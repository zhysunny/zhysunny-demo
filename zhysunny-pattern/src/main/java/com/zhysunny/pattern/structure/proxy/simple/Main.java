package com.zhysunny.pattern.structure.proxy.simple;

import com.zhysunny.pattern.structure.proxy.Printable;
import com.zhysunny.pattern.structure.proxy.Printer;

/**
 * 代理模式(简单)测试类
 * @author 章云
 * @date 2019/6/18 21:56
 */
public class Main {
    public static void main(String[] args) {
        Printable printer = new PrinterProxy(new Printer("zhy"));
        printer.print("Hello World");
    }
}
