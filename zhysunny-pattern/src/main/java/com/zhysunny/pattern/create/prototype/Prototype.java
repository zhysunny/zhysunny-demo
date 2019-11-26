package com.zhysunny.pattern.create.prototype;

import java.io.*;
import java.util.List;

/**
 * 原型类
 * @author 章云
 * @date 2019/11/26 14:22
 */
public class Prototype implements Cloneable, Serializable {

    private static final long serialVersionUID = -626776226838017796L;
    private String name;
    private int age;
    private List<String> hobby;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    /**
     * 浅拷贝
     * @return
     */
    @Override
    public Prototype clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }

    /**
     * 深复制
     */
    public Prototype deepClone() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        Prototype prototype = null;
        try {
            // 写入当前对象的二进制流
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            // 读出二进制流产生的新对象
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            prototype = (Prototype)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prototype;
    }

    @Override
    public String toString() {
        return "Prototype{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", hobby=" + hobby +
        '}';
    }

}
