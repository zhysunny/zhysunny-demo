package com.zhysunny.java.structure.sort;

/**
 * 插入排序，二分插入排序
 * @author 章云
 * @date 2019/11/29 10:19
 */
public class InsertionSort2 extends AbstractArraySort {

    public InsertionSort2(int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        int len = sort.length;
        for (int i = 1; i < len; i++) {
            int temp = sort[i];  //存储待排序的元素值
            if (sort[i - 1] > temp) {  //比有序数组的最后一个元素要小
                int insertIndex = binarySearch(0, i - 1, temp); //获取应插入位置的下标
                for (int j = i; j > insertIndex; j--) {  //将有序数组中，插入点之后的元素后移一位
                    sort[j] = sort[j - 1];
                }
                sort[insertIndex] = temp;  //插入待排序元素到正确的位置
            }
        }
    }

}
