package com.zhysunny.pattern.create.factoryMethod.impl;

import com.zhysunny.pattern.create.factoryMethod.Factory;
import com.zhysunny.pattern.create.factoryMethod.Sender;

/**
 * EML发送方式工厂
 * @author 章云
 * @date 2019/6/18 21:50
 */
public class EMLFactory implements Factory {

    @Override
    public Sender create() {
        return new EMLSender();
    }
}
