package com.zhysunny.pattern.structure.proxy.cglib;

import com.zhysunny.pattern.structure.proxy.Printable;
import com.zhysunny.pattern.structure.proxy.Printer;

/**
 * cglib代理模式(动态)测试类
 * @author 章云
 * @date 2019/6/18 21:56
 */
public class Main {
    public static void main(String[] args) {
        Printable printer = (Printable)new PrinterProxy(new Printer("zhy")).newProxyInstance();
        printer.print("Hello World");
    }
}
