package com.zhysunny.java.structure.sort;

/**
 * 堆排序
 * @author 章云
 * @date 2019/11/29 15:49
 */
public class HeapSort extends AbstractArraySort {

    public HeapSort(int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        buildHeap();
        System.out.println("建堆：");
        printTree(sort.length);
        int lastIndex = sort.length - 1;
        while (lastIndex > 0) {
            swap(0, lastIndex);  //取出堆顶元素，将堆底放入堆顶。其实就是交换下标为0与lastIndex的数据
            if (--lastIndex == 0) {
                break;  //只有一个元素时就不用调整堆了，排序结束
            }
            adjustHeap(0, lastIndex);  //调整堆
            System.out.println("调整堆：");
            printTree(lastIndex + 1);
        }
    }

    private void swap(int rootIndex, int lastIndex) {
        int temp = sort[rootIndex];
        sort[rootIndex] = sort[lastIndex];
        sort[lastIndex] = temp;
    }

    /**
     * 用数组中的元素建堆
     */
    private void buildHeap() {
        int lastIndex = sort.length - 1;
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) { //(lastIndex-1)/2就是最后一个元素的根节点的下标，依次调整每棵子树
            adjustHeap(i, lastIndex);  //调整以下标i的元素为根的子树
        }
    }

    /**
     * 调整以下标是rootIndex的元素为根的子树
     * @param rootIndex 根的下标
     * @param lastIndex 堆中最后一个元素的下标
     */
    private void adjustHeap(int rootIndex, int lastIndex) {
        int biggerIndex = rootIndex;
        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = 2 * rootIndex + 2;
        if (rightChildIndex <= lastIndex) {  //存在右子节点，则必存在左子节点
            if (sort[rootIndex] < sort[leftChildIndex] || sort[rootIndex] < sort[rightChildIndex]) { //子节点中存在比根更大的元素
                biggerIndex = sort[leftChildIndex] < sort[rightChildIndex] ? rightChildIndex : leftChildIndex;
            }
        } else if (leftChildIndex <= lastIndex) {  //只存在左子节点
            if (sort[leftChildIndex] > sort[rootIndex]) {  //左子节点更大
                biggerIndex = leftChildIndex;
            }
        }
        if (biggerIndex != rootIndex) {  //找到了比根更大的子节点
            swap(rootIndex, biggerIndex);
            //交换位置后可能会破坏子树，将焦点转向交换了位置的子节点，调整以它为根的子树
            adjustHeap(biggerIndex, lastIndex);
        }
    }

    /**
     * 将数组按照完全二叉树的形式打印出来
     */
    private void printTree(int len) {
        int layers = (int)Math.floor(Math.log((double)len) / Math.log((double)2)) + 1;  //树的层数
        int maxWidth = (int)Math.pow(2, layers) - 1;  //树的最大宽度
        int endSpacing = maxWidth;
        int spacing;
        int numberOfThisLayer;
        for (int i = 1; i <= layers; i++) {  //从第一层开始，逐层打印
            endSpacing = endSpacing / 2;  //每层打印之前需要打印的空格数
            spacing = 2 * endSpacing + 1;  //元素之间应该打印的空格数
            numberOfThisLayer = (int)Math.pow(2, i - 1);  //该层要打印的元素总数
            int j;
            for (j = 0; j < endSpacing; j++) {
                System.out.print("  ");
            }
            int beginIndex = (int)Math.pow(2, i - 1) - 1;  //该层第一个元素对应的数组下标
            for (j = 1; j <= numberOfThisLayer; j++) {
                System.out.print(sort[beginIndex++] + "");
                for (int k = 0; k < spacing; k++) {  //打印元素之间的空格
                    System.out.print("  ");
                }
                if (beginIndex == len) {  //已打印到最后一个元素
                    break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
