package com.zhysunny.java.structure.data.link;

/**
 * 链表节点封装类
 * @author 章云
 * @date 2019/9/21 10:03
 */
public class Link {
    public int age;
    public String name;
    public Link next;  //指向该链结点的下一个链结点
    public Link previous;  //指向前一个链结点

    //构造方法
    public Link(int age, String name) {
        this.age = age;
        this.name = name;
    }

    //打印该链结点的信息
    public void displayLink() {
        System.out.println("name:" + name + ",age:" + age);
    }
}
