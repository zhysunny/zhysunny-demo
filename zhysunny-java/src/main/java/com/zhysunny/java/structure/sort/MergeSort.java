package com.zhysunny.java.structure.sort;

/**
 * 归并排序
 * @author 章云
 * @date 2019/11/29 14:53
 */
public class MergeSort extends AbstractArraySort {

    public MergeSort(int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        int[] workSpace = new int[sort.length]; //用于辅助排序的数组
        recursiveMergeSort(workSpace, 0, workSpace.length - 1);
    }

    /**
     * 递归的归并排序
     * @param workSpace  辅助排序的数组
     * @param lowerBound 欲归并数组段的最小下标
     * @param upperBound 欲归并数组段的最大下标
     */
    private void recursiveMergeSort(int[] workSpace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound) {  //该段只有一个元素，不用排序
            return;
        } else {
            int mid = (lowerBound + upperBound) / 2;
            recursiveMergeSort(workSpace, lowerBound, mid);    //对低位段归并排序
            recursiveMergeSort(workSpace, mid + 1, upperBound);  //对高位段归并排序
            merge(workSpace, lowerBound, mid, upperBound);
        }
    }

    /**
     * 对数组array中的两段进行合并，lowerBound~mid为低位段，mid+1~upperBound为高位段
     * @param workSpace  辅助归并的数组，容纳归并后的元素
     * @param lowerBound 合并段的起始下标
     * @param mid        合并段的中点下标
     * @param upperBound 合并段的结束下标
     */
    private void merge(int[] workSpace, int lowerBound, int mid, int upperBound) {

        int lowBegin = lowerBound;  //低位段的起始下标
        int lowEnd = mid;           //低位段的结束下标
        int highBegin = mid + 1;  //高位段的起始下标
        int highEnd = upperBound;  //高位段的结束下标
        int j = 0; //workSpace的下标指针
        int n = upperBound - lowerBound + 1;  //归并的元素总数

        while (lowBegin <= lowEnd && highBegin <= highEnd) {
            if (sort[lowBegin] < sort[highBegin]) {//将两者较小的那个放到workSpace中
                workSpace[j++] = sort[lowBegin++];
            } else {
                workSpace[j++] = sort[highBegin++];
            }
        }

        while (lowBegin <= lowEnd) {
            workSpace[j++] = sort[lowBegin++];
        }

        while (highBegin <= highEnd) {
            workSpace[j++] = sort[highBegin++];
        }

        for (j = 0; j < n; j++) {  //将归并好的元素复制到array中
            sort[lowerBound++] = workSpace[j];
        }

    }

}
