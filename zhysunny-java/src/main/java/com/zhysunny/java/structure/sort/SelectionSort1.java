package com.zhysunny.java.structure.sort;

/**
 * 选择排序，一轮循环找到最小值，然后交换一次
 * @author 章云
 * @date 2019/11/28 17:24
 */
public class SelectionSort1 extends AbstractArraySort {

    public SelectionSort1(int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        for (int j = 0; j < sort.length - 1; j++) {
            int min = sort[j];
            int pos = 0;
            for (int i = j + 1; i < sort.length; i++) {
                if (min > sort[i]) {
                    min = sort[i];
                    pos = i;
                }
            }
            if (pos != 0) {
                sort[pos] = sort[j];
                sort[j] = min;
            }
        }
    }

}
