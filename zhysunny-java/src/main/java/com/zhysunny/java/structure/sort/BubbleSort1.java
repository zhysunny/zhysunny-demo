package com.zhysunny.java.structure.sort;

/**
 * 冒泡排序，基本思想
 * @author 章云
 * @date 2019/11/23 15:10
 */

public class BubbleSort1 extends AbstractArraySort {

    public BubbleSort1(int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
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

