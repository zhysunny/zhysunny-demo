package com.zhysunny.pattern.other.simpleFactory.general;

import com.zhysunny.pattern.other.simpleFactory.Sender;

/**
 * 简单工厂模式(一般)测试类
 * @author 章云
 * @date 2019/6/18 21:54
 */
public class Main {
	public static void main(String[] args) {
		SenderFactory factory = new SenderFactory();
		Sender emlSender = factory.create(SenderFactory.EML);
		emlSender.send();
		Sender memSender = factory.create(SenderFactory.MEM);
		memSender.send();
		Sender mmsSender = factory.create(SenderFactory.MMS);
		mmsSender.send();
	}
}
