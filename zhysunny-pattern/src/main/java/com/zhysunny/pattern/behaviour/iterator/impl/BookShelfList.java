package com.zhysunny.pattern.behaviour.iterator.impl;

import com.zhysunny.pattern.behaviour.iterator.BookShelf;
import com.zhysunny.pattern.behaviour.iterator.SelfIterator;
import com.zhysunny.pattern.behaviour.iterator.bean.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 书架对象，存储书的集合(List)
 * @author 章云
 * @date 2019/5/10 10:20
 */
public class BookShelfList implements BookShelf<Book> {

	/**
	 * 书的集合
	 */
	private List<Book> books;
	/**
	 * 数组最后存储的索引位置
	 */
	private int last = 0;

	public BookShelfList(int length) {
		if (length < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + length);
		}
		this.books = new ArrayList<Book>(length);
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
