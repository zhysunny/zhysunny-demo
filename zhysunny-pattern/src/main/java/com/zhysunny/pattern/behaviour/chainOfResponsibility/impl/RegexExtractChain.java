package com.zhysunny.pattern.behaviour.chainOfResponsibility.impl;

import com.zhysunny.pattern.behaviour.chainOfResponsibility.AbstractExtract;

/**
 * 正则提取
 * @author 章云
 * @date 2019/5/28 21:39
 */
public class RegexExtractChain extends AbstractExtract {
    @Override
    public void extract() {
        System.out.println("正则提取步骤");
    }
}
