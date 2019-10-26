package com.zhysunny.pattern.other.simpleFactory.staticFactory;

import com.zhysunny.pattern.other.simpleFactory.Sender;

/**
 * 简单工厂模式(静态方法)测试类
 * @author 章云
 * @date 2019/6/18 21:56
 */
public class Main {
    public static void main(String[] args) {
        Sender emlSender = SenderFactory.createEml();
        emlSender.send();
        Sender memSender = SenderFactory.createMem();
        memSender.send();
        Sender mmsSender = SenderFactory.createMms();
        mmsSender.send();
    }
}
