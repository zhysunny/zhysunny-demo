package com.zhysunny.pattern.other.simpleFactory.impl;

import com.zhysunny.pattern.other.simpleFactory.Sender;

/**
 * 短信方式发送
 * @author 章云
 * @date 2019/5/13 9:38
 */
public class MEMSender implements Sender {

	public static final String TYPE = "MEM";

	@Override
	public void send() {
		System.out.println("发送短信");
	}

}
