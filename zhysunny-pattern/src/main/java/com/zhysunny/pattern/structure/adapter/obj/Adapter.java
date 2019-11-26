package com.zhysunny.pattern.structure.adapter.obj;

import com.zhysunny.pattern.structure.adapter.Source;
import com.zhysunny.pattern.structure.adapter.Targetable;

/**
 * 对象适配器
 * @author 章云
 * @date 2019/11/26 16:09
 */
public class Adapter implements Targetable {

    private Source source;

    public Adapter(Source source) {
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

}
