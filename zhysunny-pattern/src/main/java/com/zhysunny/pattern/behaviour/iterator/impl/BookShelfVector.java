package com.zhysunny.pattern.behaviour.iterator.impl;

import com.zhysunny.pattern.behaviour.iterator.BookShelf;
import com.zhysunny.pattern.behaviour.iterator.SelfIterator;
import com.zhysunny.pattern.behaviour.iterator.bean.Book;

import java.util.Iterator;
import java.util.Vector;

/**
 * 书架对象，存储书的集合(Vector)
 * @author 章云
 * @date 2019/5/10 10:20
 */
public class BookShelfVector implements BookShelf<Book> {

	/**
	 * 书的集合
	 */
	private Vector<Book> books;
	/**
	 * 数组最后存储的索引位置
	 */
	private int last = 0;

	public BookShelfVector(int length) {
		if (length < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + length);
		}
		this.books = new Vector<Book>(length);
	}

	@Override
	public void setBookAt(int index, Book book) {
		this.books.add(index, book);
	}

	@Override
	public Book getBookAt(int index) {
		return this.books.get(index);
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
