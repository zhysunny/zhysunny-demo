package com.zhysunny.pattern.create.abstractFactory.impl;

import com.zhysunny.pattern.create.abstractFactory.ICat;

/**
 * 产品实现类(黑猫)
 * @author 章云
 * @date 2019/7/15 15:32
 */
public class BlackCat implements ICat {
    @Override
    public void call() {
        System.out.println("黑色的猫在叫");
    }
}
