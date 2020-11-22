package com.zhysunny.pattern.create.factoryMethod.impl;

import com.zhysunny.pattern.create.factoryMethod.Sender;

/**
 * 彩信方式发送
 * @author 章云
 * @date 2019/5/13 9:38
 */
public class MMSSender implements Sender {

	@Override
	public void send() {
		System.out.println("发送彩信");
	}

}
