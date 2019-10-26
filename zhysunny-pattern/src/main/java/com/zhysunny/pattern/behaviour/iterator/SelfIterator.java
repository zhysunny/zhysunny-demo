package com.zhysunny.pattern.behaviour.iterator;

import java.util.Iterator;

/**
 * 迭代规则
 * @author 章云
 * @date 2019/6/18 21:58
 */
public class SelfIterator<E> implements Iterator<E> {
	/**
	 * 遍历的索引位置
	 */
	private int index;
	/**
	 * 需要遍历的集合对象
	 */
	private BookShelf<E> iterable;

	public SelfIterator(BookShelf<E> iterable) {
		this.iterable = iterable;
		this.index = 0;
	}

	@Override
	public boolean hasNext() {
		if (index < this.iterable.size()) {
			return true;
		}
		return false;
	}

	@Override
	public E next() {
		E e = this.iterable.getBookAt(index);
		index++;
		return e;
	}

}
