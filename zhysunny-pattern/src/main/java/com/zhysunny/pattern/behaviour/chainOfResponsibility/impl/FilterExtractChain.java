package com.zhysunny.pattern.behaviour.chainOfResponsibility.impl;

import com.zhysunny.pattern.behaviour.chainOfResponsibility.AbstractExtract;

/**
 * 关键字过滤
 * @author 章云
 * @date 2019/5/28 21:39
 */
public class FilterExtractChain extends AbstractExtract {
    @Override
    public void extract() {
        System.out.println("关键字过滤步骤");
    }
}
