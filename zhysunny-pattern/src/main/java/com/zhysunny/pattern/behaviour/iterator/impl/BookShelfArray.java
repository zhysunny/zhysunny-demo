package com.zhysunny.pattern.behaviour.iterator.impl;

import com.zhysunny.pattern.behaviour.iterator.BookShelf;
import com.zhysunny.pattern.behaviour.iterator.SelfIterator;
import com.zhysunny.pattern.behaviour.iterator.bean.Book;

import java.util.Iterator;

/**
 * 书架对象，存储书的集合(数组)
 * @author 章云
 * @date 2019/5/10 10:19
 */
public class BookShelfArray implements BookShelf<Book> {

    /**
     * 书的集合
     */
    private Book[] books;
    /**
     * 数组最后存储的索引位置
     */
    private int last = 0;

    public BookShelfArray(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + length);
        }
        this.books = new Book[length];
    }

    @Override
    public void setBookAt(int index, Book book) {
        this.books[index] = book;
    }

    @Override
    public Book getBookAt(int index) {
        return books[index];
    }

    @Override
    public void appendBook(Book book) {
        setBookAt(last, book);
        last++;
    }

    @Override
    public int size() {
        return last;
    }

    @Override
    public Iterator<Book> iterator() {
        return new SelfIterator<Book>(this);
    }

}
