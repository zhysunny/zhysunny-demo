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
    private String name;

    public PrinterProxy(String name) {
        this.name = name;
    }

    /**
     * 希望执行print方法前实例化被代理对象
     */
    private void before() {
        printer = new Printer(this.name);
    }

    private void after() {
        System.out.println("调用after方法");
    }

    @Override
    public void print(String string) {
        before();
        printer.print(string);
        after();
    }
}
