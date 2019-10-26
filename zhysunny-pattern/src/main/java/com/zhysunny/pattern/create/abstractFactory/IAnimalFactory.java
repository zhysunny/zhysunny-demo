package com.zhysunny.pattern.create.abstractFactory;

/**
 * 工厂类接口(动物工厂)
 * @author 章云
 * @date 2019/7/15 15:23
 */
public interface IAnimalFactory {
    /**
     * 创建猫
     * @return
     */
    ICat createCat();

    /**
     * 创建狗
     * @return
     */
    IDog createDog();
}
