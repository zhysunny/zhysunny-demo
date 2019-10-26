package com.zhysunny.pattern.other.simpleFactory.general;

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

	public static final String MEM = "MEM";
	public static final String EML = "EML";
	public static final String MMS = "MMS";

	public Sender create(String type) {
		if (MEM.equalsIgnoreCase(type)) {
			return new MEMSender();
		} else if (MMS.equalsIgnoreCase(type)) {
			return new MMSSender();
		} else if (EML.equalsIgnoreCase(type)) {
			return new EMLSender();
		} else {
			return null;
		}
	}

}
