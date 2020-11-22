package com.zhysunny.pattern.structure.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 对代理类的功能增强
 * @author 章云
 * @date 2019/6/18 21:52
 */
public class PrinterProxy implements InvocationHandler {

    private Object target;

    public PrinterProxy(Object target) {
        this.target = target;
    }

    public final Object newProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行方法前");
        Object obj = method.invoke(target, args);
        System.out.println("执行方法后");
        return obj;
    }

}
