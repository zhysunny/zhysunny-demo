package com.zhysunny.pattern.structure.adapter.clz;

import com.zhysunny.pattern.structure.adapter.Source;
import com.zhysunny.pattern.structure.adapter.Targetable;

/**
 * 类适配器
 * @author 章云
 * @date 2019/11/26 16:09
 */
public class Adapter extends Source implements Targetable {

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

}
