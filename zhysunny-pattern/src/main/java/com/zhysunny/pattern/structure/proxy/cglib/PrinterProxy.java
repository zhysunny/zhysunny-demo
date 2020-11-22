package com.zhysunny.pattern.structure.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * 对代理类的功能增强
 * 实现了一个方法拦截器接口
 * @author 章云
 * @date 2019/6/18 21:52
 */
public class PrinterProxy implements MethodInterceptor {

    private Object target;

    public PrinterProxy(Object target) {
        this.target = target;

    }

    public final Object newProxyInstance() {
        //动态生成一个新的类，使用父类的无参构造方法创建一个指定了特定回调的代理实例
        //增强器，动态代码生成器
        Enhancer enhancer = new Enhancer();
        //回调方法
        enhancer.setCallback(this);
        //设置生成类的父类类型
        enhancer.setSuperclass(target.getClass());
        //动态生成字节码并返回代理对象
        return enhancer.create(new Class[]{ String.class }, new Object[]{ "" });
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行方法前");
        Object obj = method.invoke(target, args);
        System.out.println("执行方法后");
        return obj;
    }
}
