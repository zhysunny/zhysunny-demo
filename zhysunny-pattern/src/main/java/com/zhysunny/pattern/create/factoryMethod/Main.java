package com.zhysunny.pattern.create.factoryMethod;

import com.zhysunny.pattern.create.factoryMethod.impl.EMLFactory;
import com.zhysunny.pattern.create.factoryMethod.impl.MEMFactory;
import com.zhysunny.pattern.create.factoryMethod.impl.MMSFactory;

/**
 * 工厂方法模式测试类
 * @author 章云
 * @date 2019/6/18 21:53
 */
public class Main {
    public static void main(String[] args) {
        new EMLFactory().create().send();
        new MEMFactory().create().send();
        new MMSFactory().create().send();
    }
}
