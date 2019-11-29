package com.zhysunny.java.structure.sort;

/**
 * 冒泡排序，当一轮没发生交换时，排序结束
 * @author 章云
 * @date 2019/11/29 9:36
 */
public class BubbleSort2 extends AbstractArraySort {

    public BubbleSort2(int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
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
