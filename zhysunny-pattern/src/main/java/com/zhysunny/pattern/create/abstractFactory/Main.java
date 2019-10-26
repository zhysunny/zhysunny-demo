package com.zhysunny.pattern.create.abstractFactory;

import com.zhysunny.pattern.create.abstractFactory.impl.BlackAnimalFactory;
import com.zhysunny.pattern.create.abstractFactory.impl.WhiteAnimalFactory;

/**
 * 抽象工厂模式测试类
 * @author 章云
 * @date 2019/7/15 15:36
 */
public class Main {
    public static void main(String[] args) {
        IAnimalFactory blackAnimalFactory = new BlackAnimalFactory();
        ICat blackCat = blackAnimalFactory.createCat();
        blackCat.call();
        IDog blackDog = blackAnimalFactory.createDog();
        blackDog.call();

        IAnimalFactory whiteAnimalFactory = new WhiteAnimalFactory();
        ICat whiteCat = whiteAnimalFactory.createCat();
        whiteCat.call();
        IDog whiteDog = whiteAnimalFactory.createDog();
        whiteDog.call();
    }
}
