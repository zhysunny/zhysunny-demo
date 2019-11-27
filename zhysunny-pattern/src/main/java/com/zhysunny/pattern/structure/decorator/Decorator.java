package com.zhysunny.pattern.structure.decorator;

/**
 * 装饰类
 * @author 章云
 * @date 2019/11/27 10:36
 */
public class Decorator implements Sourceable {

    private Sourceable source;

    public Decorator(Sourceable source) {
        this.source = source;
    }

    @Override
    public void method() {
        System.out.println("before decorator!");
        source.method();
        System.out.println("after decorator!");
    }

}
