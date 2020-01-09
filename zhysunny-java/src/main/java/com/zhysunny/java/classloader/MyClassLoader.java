package com.zhysunny.java.classloader;

import java.io.*;
import java.net.URISyntaxException;

/**
 * @author 章云
 * @date 2020/1/9 10:52
 */
public class MyClassLoader extends ClassLoader {

    public MyClassLoader() {
        super(ClassLoader.getSystemClassLoader());
    }

    @Override
    protected Class<?> findClass(String className) {
        byte[] b = new byte[0];
        try {
            b = loadClassFile(className);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return super.defineClass(className, b, 0, b.length);
    }

    private byte[] loadClassFile(String className) throws URISyntaxException {
        File file = new File(
        MyClassLoader.class.getClassLoader().getResource(className.replace(".", "/") + ".class").toURI());
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fis.read()) != -1) {
                baos.write(b);
            }
            fis.close();
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
