package com.zhysunny.pattern.structure.proxy.simple;

import com.zhysunny.pattern.structure.proxy.Printable;
import com.zhysunny.pattern.structure.proxy.Printer;

/**
 * 代理类，简单代理只能针对特定的类
 * @author 章云
 * @date 2019/6/18 21:58
 */
public class PrinterProxy implements Printable {

    private Printer printer;

    public PrinterProxy(Printer printer) {
        this.printer = printer;
    }

    /**
     * 希望执行print方法前实例化被代理对象
     */
    private void before() {
        System.out.println("执行方法前");
    }

    private void after() {
        System.out.println("执行方法后");
    }

    @Override
    public void print(String string) {
        before();
        printer.print(string);
        after();
    }
}
