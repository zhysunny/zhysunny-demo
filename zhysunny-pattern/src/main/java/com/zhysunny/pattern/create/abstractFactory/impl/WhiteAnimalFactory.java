package com.zhysunny.pattern.create.abstractFactory.impl;

import com.zhysunny.pattern.create.abstractFactory.IAnimalFactory;
import com.zhysunny.pattern.create.abstractFactory.ICat;
import com.zhysunny.pattern.create.abstractFactory.IDog;

/**
 * 工厂实现类(创建白色动物)
 * @author 章云
 * @date 2019/7/15 15:27
 */
public class WhiteAnimalFactory implements IAnimalFactory {
    @Override
    public ICat createCat() {
        return new WhiteCat();
    }

    @Override
    public IDog createDog() {
        return new WhiteDog();
    }
}
