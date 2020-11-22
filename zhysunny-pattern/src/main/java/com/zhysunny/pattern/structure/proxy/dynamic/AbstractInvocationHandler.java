package com.zhysunny.pattern.structure.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author 章云
 * @date 2019/6/18 21:49
 */
public abstract class AbstractInvocationHandler implements InvocationHandler {

    protected Object target;

    AbstractInvocationHandler(Object target) {
        this.target = target;
    }

    public final Object newProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

}
