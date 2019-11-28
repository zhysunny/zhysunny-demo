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
        sort3();
    }

    public int[] getSort() {
        return sort;
    }

    private void copy() {
        this.sort = Arrays.copyOf(array, array.length);
    }

    private void sort1() {
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

    private void sort2() {
        if (sort.length > 1) {
            for (int j = 0; j < sort.length - 1; j++) {
                int max = sort[0];
                boolean exchange = false;  //设置交换变量
                for (int i = 0; i < sort.length - 1 - j; i++) {
                    if (max < sort[i + 1]) {
                        max = sort[i + 1];
                    } else {
                        sort[i] = sort[i + 1];
                        sort[i + 1] = max;
                        if (!exchange) {
                            exchange = true;  //发生了交换操作
                        }
                    }
                }
                if (!exchange) {
                    break;  //如果上一轮没有发生交换数据，证明已经是有序的了，结束排序
                }
            }
        }
    }

    /**
     * 一次循环获得最大值和最小值
     */
    private void sort3() {
        if (sort.length > 1) {
            for (int j = 0; j < sort.length / 2; j++) {
                int min = sort[j];
                int max = sort[sort.length - 1 - j];
                if (min > max) {
                    sort[j] = max;
                    sort[sort.length - 1 - j] = min;
                    min = sort[j];
                    max = sort[sort.length - 1 - j];
                }
                for (int i = j; i < sort.length - 1 - j; i++) {
                    if (min > sort[i + 1]) {
                        min = sort[i + 1];
                        sort[i + 1] = sort[j];
                        sort[j] = min;
                    }
                    if (max < sort[i + 1]) {
                        max = sort[i + 1];
                        sort[i + 1] = sort[sort.length - 1 - j];
                        sort[sort.length - 1 - j] = max;
                    }
                }
            }
        }
    }

}
