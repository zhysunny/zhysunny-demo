package com.zhysunny.java.structure.sort;

/**
 * 插入排序，基本思想
 * @author 章云
 * @date 2019/11/29 10:19
 */
public class InsertionSort1 extends AbstractArraySort {

    public InsertionSort1(int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        int len = sort.length;
        for (int i = 1; i < len; i++) {
            int temp = sort[i];  //存储待排序的元素值
            int insertPoint = i - 1;  //与待排序元素值作比较的元素的下标
            while (insertPoint >= 0 && sort[insertPoint] > temp) { //当前元素比待排序元素大
                sort[insertPoint + 1] = sort[insertPoint];  //当前元素后移一位
                insertPoint--;
            }
            sort[insertPoint + 1] = temp;  //找到了插入位置，插入待排序元素
        }
    }

}
