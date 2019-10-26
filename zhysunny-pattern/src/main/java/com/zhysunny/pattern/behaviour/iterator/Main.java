package com.zhysunny.pattern.behaviour.iterator;

import com.zhysunny.pattern.behaviour.iterator.bean.Book;
import com.zhysunny.pattern.behaviour.iterator.impl.BookShelfArray;

import java.util.Iterator;

/**
 * 迭代器模式测试类
 * @author 章云
 * @date 2019/6/18 21:53
 */
public class Main {
	public static void main(String[] args) {
		BookShelf bookShelf = new BookShelfArray(4);
		bookShelf.appendBook(new Book("线性代数"));
		bookShelf.appendBook(new Book("自然语言处理"));
		bookShelf.appendBook(new Book("数据挖掘"));
		bookShelf.appendBook(new Book("图解设计模式"));

		Iterator<Book> iterator = bookShelf.iterator();
		Book book = null;
		while (iterator.hasNext()) {
			book = iterator.next();
			System.out.println(book.getName());
		}
	}
}
