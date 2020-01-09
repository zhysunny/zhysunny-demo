package com.zhysunny.java.classloader;

/**
 * @author 章云
 * @date 2020/1/9 10:49
 */
public class PrintServiceImpl implements PrintService {

    @Override
    public void print() {
        // 运行期间修改打印
        System.out.println("zhysunny");
    }

}
