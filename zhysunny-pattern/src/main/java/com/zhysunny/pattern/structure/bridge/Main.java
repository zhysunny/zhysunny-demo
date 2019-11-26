package com.zhysunny.pattern.structure.bridge;

/**
 * @author 章云
 * @date 2019/11/26 16:36
 */
public class Main {

    public static void main(String[] args) {
        Bridge bridge = new MyBridge();
        // 调用第一个对象
        Sourceable sub1 = new SourceSub1();
        bridge.setSource(sub1);
        bridge.method();
        // 调用第二个对象
        Sourceable sub2 = new SourceSub2();
        bridge.setSource(sub2);
        bridge.method();
    }

}
