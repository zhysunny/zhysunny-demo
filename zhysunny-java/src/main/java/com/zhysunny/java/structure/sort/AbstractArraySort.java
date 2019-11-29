package com.zhysunny.java.structure.sort;

import java.util.Arrays;

/**
 * @author 章云
 * @date 2019/11/29 9:34
 */
public abstract class AbstractArraySort {

    private int[] array;
    protected int[] sort;

    public AbstractArraySort(int[] array) {
        this.array = array;
        copy();
        if (array.length > 1) {
            this.sort();
        }
    }

    public int[] getSorted() {
        return sort;
    }

    protected void copy() {
        this.sort = Arrays.copyOf(array, array.length);
    }

    protected abstract void sort();

}
