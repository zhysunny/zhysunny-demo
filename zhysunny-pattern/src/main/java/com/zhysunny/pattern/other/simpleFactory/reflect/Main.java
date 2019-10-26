package com.zhysunny.pattern.other.simpleFactory.reflect;

import com.zhysunny.pattern.other.simpleFactory.Sender;
import com.zhysunny.pattern.other.simpleFactory.impl.EMLSender;
import com.zhysunny.pattern.other.simpleFactory.impl.MEMSender;
import com.zhysunny.pattern.other.simpleFactory.impl.MMSSender;

/**
 * 简单工厂模式(反射)测试类
 * @author 章云
 * @date 2019/6/18 21:55
 */
public class Main {
    public static void main(String[] args) {
        SenderFactory factory = new SenderFactory();
        Sender emlSender = factory.create(EMLSender.TYPE);
        emlSender.send();
        Sender memSender = factory.create(MEMSender.TYPE);
        memSender.send();
        Sender mmsSender = factory.create(MMSSender.TYPE);
        mmsSender.send();
    }
}
