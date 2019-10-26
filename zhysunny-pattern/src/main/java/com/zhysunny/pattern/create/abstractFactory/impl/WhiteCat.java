package com.zhysunny.pattern.create.abstractFactory.impl;

import com.zhysunny.pattern.create.abstractFactory.ICat;

/**
 * 产品实现类(白猫)
 * @author 章云
 * @date 2019/7/15 15:34
 */
public class WhiteCat implements ICat {
    @Override
    public void call() {
        System.out.println("白色的猫在叫");
    }
}
