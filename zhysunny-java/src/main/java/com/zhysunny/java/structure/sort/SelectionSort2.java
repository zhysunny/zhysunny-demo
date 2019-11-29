package com.zhysunny.java.structure.sort;

/**
 * 选择排序，一轮循环找到最小值和最大值，然后交换
 * @author 章云
 * @date 2019/11/28 17:24
 */
public class SelectionSort2 extends AbstractArraySort {

    public SelectionSort2(int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        for (int j = 0; j < sort.length / 2; j++) {
            int min = sort[j];
            int max = sort[sort.length - j - 1];
            int minPos = 0;
            int maxPos = 0;
            if (min > max) {
                sort[j] = max;
                max = sort[sort.length - j - 1] = min;
                min = sort[j];
            }
            for (int i = j + 1; i < sort.length - j - 1; i++) {
                if (min > sort[i]) {
                    min = sort[i];
                    minPos = i;
                }
                if (max < sort[i]) {
                    max = sort[i];
                    maxPos = i;
                }
            }
            if (minPos != 0) {
                sort[minPos] = sort[j];
                sort[j] = min;
            }
            if (maxPos != 0) {
                sort[maxPos] = sort[sort.length - j - 1];
                sort[sort.length - j - 1] = max;
            }
        }
    }

}
