package com.zhysunny.pattern.create.factoryMethod.impl;

import com.zhysunny.pattern.create.factoryMethod.Sender;

/**
 * 邮件方式发送
 * @author 章云
 * @date 2019/5/13 9:39
 */
public class EMLSender implements Sender {

    @Override
    public void send() {
        System.out.println("发送邮件");
    }

}
