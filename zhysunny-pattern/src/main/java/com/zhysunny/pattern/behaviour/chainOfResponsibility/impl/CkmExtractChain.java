package com.zhysunny.pattern.behaviour.chainOfResponsibility.impl;

import com.zhysunny.pattern.behaviour.chainOfResponsibility.AbstractExtract;

/**
 * Ckm提取
 * @author 章云
 * @date 2019/5/28 21:39
 */
public class CkmExtractChain extends AbstractExtract {
    @Override
    public void extract() {
        System.out.println("Ckm提取步骤");
    }
}
