package com.zhysunny.pattern.structure.proxy.dynamic;

import java.lang.reflect.Method;

/**
 * 对代理类的功能增强
 * @author 章云
 * @date 2019/6/18 21:52
 */
public class LoggerHandler extends AbstractInvocationHandler {

    public LoggerHandler(Object target) {
        super(target);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行方法前");
        Object obj = method.invoke(target, args);
        System.out.println("执行方法后");
        return obj;
    }

}
