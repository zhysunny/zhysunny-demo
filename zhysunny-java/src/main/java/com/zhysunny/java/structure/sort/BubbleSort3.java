package com.zhysunny.java.structure.sort;

/**
 * 冒泡排序，一次循环获得最大值和最小值
 * @author 章云
 * @date 2019/11/29 9:36
 */
public class BubbleSort3 extends AbstractArraySort {

    public BubbleSort3(int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        for (int j = 0; j < sort.length / 2; j++) {
            int min = sort[j];
            int max = sort[sort.length - 1 - j];
            if (min > max) {
                sort[j] = max;
                max = sort[sort.length - j - 1] = min;
                min = sort[j];
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
