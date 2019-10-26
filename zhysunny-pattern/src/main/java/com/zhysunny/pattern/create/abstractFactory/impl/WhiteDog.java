package com.zhysunny.pattern.create.abstractFactory.impl;

import com.zhysunny.pattern.create.abstractFactory.IDog;

/**
 * 产品实现类(白狗)
 * @author 章云
 * @date 2019/7/15 15:34
 */
public class WhiteDog implements IDog {
    @Override
    public void call() {
        System.out.println("白色的狗在叫");
    }
}
