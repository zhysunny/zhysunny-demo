package com.zhysunny.pattern.other.simpleFactory.staticFactory;

import com.zhysunny.pattern.other.simpleFactory.Sender;
import com.zhysunny.pattern.other.simpleFactory.impl.EMLSender;
import com.zhysunny.pattern.other.simpleFactory.impl.MEMSender;
import com.zhysunny.pattern.other.simpleFactory.impl.MMSSender;

/**
 * 信息发送工厂类
 * @author 章云
 * @date 2019/5/13 9:39
 */
public class SenderFactory {

    public static Sender createMem() {
        return new MEMSender();
    }

    public static Sender createMms() {
        return new MMSSender();
    }

    public static Sender createEml() {
        return new EMLSender();
    }

}
