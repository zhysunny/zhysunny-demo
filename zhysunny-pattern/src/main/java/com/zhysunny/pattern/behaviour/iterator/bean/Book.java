package com.zhysunny.pattern.behaviour.iterator.bean;

/**
 * 书对象，存储书的属性信息
 * @author 章云
 * @date 2019/5/10 10:13
 */
public class Book {

    /**
     * 书名
     */
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
