package com.zhysunny.pattern.other.simpleFactory.moreMethod;

import com.zhysunny.pattern.other.simpleFactory.Sender;

/**
 * 简单工厂模式(多方法)测试类
 * @author 章云
 * @date 2019/6/18 21:55
 */
public class Main {
	public static void main(String[] args) {
		SenderFactory factory = new SenderFactory();
		Sender emlSender = factory.createEml();
		emlSender.send();
		Sender memSender = factory.createMem();
		memSender.send();
		Sender mmsSender = factory.createMms();
		mmsSender.send();
	}
}
