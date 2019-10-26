package com.zhysunny.pattern.other.simpleFactory.reflect;

import com.zhysunny.pattern.other.simpleFactory.Sender;
import com.zhysunny.pattern.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 信息发送工厂类
 * @author 章云
 * @date 2019/5/13 9:39
 */
public class SenderFactory {

    private static Map<String, Sender> senderMap;

    public SenderFactory() {
        senderMap = getCls();
    }

    public Sender create(String type) {
        return senderMap.get(type);
    }

    private static Map<String, Sender> getCls() {
        Map<String, Sender> senderMap = null;
        try {
            List<Class<?>> classes = ClassUtils.getClasses("com.admin.other.simpleFactory.impl");
            senderMap = new HashMap<String, Sender>(classes.size());
            Field field = null;
            for (Class<?> cls : classes) {
                field = cls.getDeclaredField("TYPE");
                senderMap.put(field.get("TYPE").toString(), (Sender) cls.newInstance());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return senderMap;
    }

}
