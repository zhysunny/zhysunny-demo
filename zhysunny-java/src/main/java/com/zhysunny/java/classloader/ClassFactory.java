package com.zhysunny.java.classloader;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 章云
 * @date 2020/1/9 10:55
 */
public class ClassFactory {

    private static Map<String, Object> map = new HashMap<>();
    private static long lastModified;

    private ClassFactory() {
        super();
    }

    public static Object getInstance(String className) throws URISyntaxException {
        File file = new File(
        MyClassLoader.class.getClassLoader().getResource(className.replace(".", "/") + ".class").toURI());
        long newModified = file.lastModified();
        // 文件第一次加载，通过反射的方式创建一个对象
        if (map.get(className) == null) {
            loadClass(className);
        }
        // .class 文件被修改过，通过ClassLoader方式 创建一个对象
        if (lastModified != newModified) {
            loadClass(className);
        }
        lastModified = newModified;
        return map.get(className);
    }

    private static void loadClass(String className) {
        MyClassLoader myClassLoader = new MyClassLoader();
        try {
            Class<?> clazz = myClassLoader.findClass(className);
            Object object = clazz.newInstance();
            map.put(className, object);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

}
