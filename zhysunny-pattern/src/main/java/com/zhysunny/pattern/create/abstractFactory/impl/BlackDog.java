package com.zhysunny.pattern.create.abstractFactory.impl;

import com.zhysunny.pattern.create.abstractFactory.IDog;

/**
 * 产品实现类(黑狗)
 * @author 章云
 * @date 2019/7/15 15:34
 */
public class BlackDog implements IDog {
    @Override
    public void call() {
        System.out.println("黑色的狗在叫");
    }
}
