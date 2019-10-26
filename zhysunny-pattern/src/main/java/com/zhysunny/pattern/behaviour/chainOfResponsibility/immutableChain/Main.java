package com.zhysunny.pattern.behaviour.chainOfResponsibility.immutableChain;

import com.zhysunny.pattern.behaviour.chainOfResponsibility.Extract;

/**
 * 责任链模式(不可变)测试类
 * @author 章云
 * @date 2019/6/18 21:52
 */
public class Main {
    public static void main(String[] args) {
        Extract extract = new ExtractChain();
        extract.extract();
    }
}
