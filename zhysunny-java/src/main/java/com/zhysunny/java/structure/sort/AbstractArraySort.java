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

    /**
     * 二分查找法
     * @param lowerBound 查找段的最小下标
     * @param upperBound 查找段的最大下标
     * @param target     目标元素
     * @return 目标元素应该插入位置的下标
     */
    protected int binarySearch(int lowerBound, int upperBound, int target) {
        int curIndex;
        while (lowerBound < upperBound) {
            curIndex = (lowerBound + upperBound) / 2;
            if (sort[curIndex] > target) {
                upperBound = curIndex - 1;
            } else {
                lowerBound = curIndex + 1;
            }
        }
        return lowerBound;
    }

}
