package com.zhysunny.pattern.behaviour.chainOfResponsibility.impl;

import com.zhysunny.pattern.behaviour.chainOfResponsibility.AbstractExtract;

/**
 * 字段翻译
 * @author 章云
 * @date 2019/5/28 21:39
 */
public class TranslateExtractChain extends AbstractExtract {
    @Override
    public void extract() {
        System.out.println("字段翻译步骤");
    }
}
