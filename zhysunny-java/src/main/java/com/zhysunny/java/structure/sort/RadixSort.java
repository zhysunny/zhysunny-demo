package com.zhysunny.java.structure.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 基数排序
 * @author 章云
 * @date 2019/11/29 16:40
 */
public class RadixSort extends AbstractArraySort {

    public RadixSort(int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        int max = sort[0];
        for (int i = 0; i < sort.length; i++) {  //找到数组中的最大值
            if (sort[i] > max) {
                max = sort[i];
            }
        }
        int keysNum = 0;  //关键字的个数，我们使用个位、十位、百位...当做关键字，所以关键字的个数就是最大值的位数
        while (max > 0) {
            max /= 10;
            keysNum++;
        }
        List<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) {  //每位可能的数字为0~9，所以设置10个桶
            buckets.add(new ArrayList<Integer>());  //桶由ArrayList<Integer>构成
        }
        for (int i = 0; i < keysNum; i++) {  //由最次关键字开始，依次按照关键字进行分配
            for (int j = 0; j < sort.length; j++) {  //扫描所有数组元素，将元素分配到对应的桶中
                //取出该元素对应第i+1位上的数字，比如258，现在要取出十位上的数字，258%100=58,58/10=5
                int key = sort[j] % (int)Math.pow(10, i + 1) / (int)Math.pow(10, i);
                buckets.get(key).add(sort[j]);  //将该元素放入关键字为key的桶中
            }
            //分配完之后，将桶中的元素依次复制回数组
            int counter = 0;  //元素计数器
            for (int j = 0; j < 10; j++) {
                ArrayList<Integer> bucket = buckets.get(j);  //关键字为j的桶
                while (bucket.size() > 0) {
                    sort[counter++] = bucket.remove(0);  //将桶中的第一个元素复制到数组，并移除
                }
            }
        }
    }

}
