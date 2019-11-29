package com.zhysunny.java.structure.sort;

/**
 * 希尔排序
 * @author 章云
 * @date 2019/11/29 15:42
 */
public class ShellSort extends AbstractArraySort {

    public ShellSort(int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        int len = sort.length;
        int h = 1;
        while (3 * h + 1 < len) {  //确定第一轮排序时的间隔
            h = 3 * h + 1;
        }
        while (h > 0) {
            for (int i = 0; i < h; i++) {
                shellInsertSort(i, h);  //对间隔为h的元素进行插入排序
            }
            h = (h - 1) / 3;  //下一轮排序的间隔
        }
    }

    /**
     * 希尔排序内部使用的插入排序:
     * 需要进行插入排序的元素为array[beginIndex]、array[beginIndex+increment]、array[beginIndex+2*increment]...
     * @param beginIndex 起始下标
     * @param increment  增量
     */
    private void shellInsertSort(int beginIndex, int increment) {
        int targetIndex = beginIndex + increment;  //欲插入元素的下标
        while (targetIndex < sort.length) {
            int temp = sort[targetIndex];
            int previousIndex = targetIndex - increment;  //前一个元素下标，间隔为increment
            while (previousIndex >= 0 && sort[previousIndex] > temp) {
                sort[previousIndex + increment] = sort[previousIndex];  //比欲插入数据项大的元素后移一位
                previousIndex = previousIndex - increment;
            }
            sort[previousIndex + increment] = temp;  //插入到合适的位置
            targetIndex = targetIndex + increment;  //插入下一个元素
        }
    }

}
