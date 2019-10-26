package com.zhysunny.pattern.behaviour.iterator;

import java.util.Iterator;

/**
 * 可迭代的接口
 * @author 章云
 * @date 2019/6/18 21:49
 */
public interface BookShelf<E> {

    /**
     * 增加元素
     * @param e
     */
    void appendBook(E e);

    /**
     * 获得迭代器
     * @return
     */
    Iterator<E> iterator();

    /**
     * 获得集合长度
     * @return
     */
    int size();

    /**
     * 给index索引设置新值
     * @param index
     * @param e
     */
    void setBookAt(int index, E e);

    /**
     * 获得index索引对应的值
     * @param index
     * @return
     */
    E getBookAt(int index);

}
