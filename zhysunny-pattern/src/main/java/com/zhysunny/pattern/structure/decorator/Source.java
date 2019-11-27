package com.zhysunny.pattern.structure.decorator;

/**
 * 原始类，被装饰类
 * @author 章云
 * @date 2019/11/27 10:29
 */
public class Source implements Sourceable {

    @Override
    public void method() {
        System.out.println("the original method!");
    }

}
