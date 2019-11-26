package com.zhysunny.java.structure.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author 章云
 * @date 2019/11/23 15:10
 */
public class BubbleSort {

    private int[] array;
    private int[] sort;

    public BubbleSort(int[] array) {
        this.array = array;
        copy();
        sort();
    }

    public int[] getSort() {
        return sort;
    }

    private void copy() {
        this.sort = Arrays.copyOf(array, array.length);
    }

    private void sort() {
        if (sort.length > 1) {
            for (int j = 0; j < sort.length - 1; j++) {
                int max = sort[0];
                for (int i = 0; i < sort.length - 1 - j; i++) {
                    if (max < sort[i + 1]) {
                        max = sort[i + 1];
                    } else {
                        sort[i] = sort[i + 1];
                        sort[i + 1] = max;
                    }
                }
            }
        }
    }

}
