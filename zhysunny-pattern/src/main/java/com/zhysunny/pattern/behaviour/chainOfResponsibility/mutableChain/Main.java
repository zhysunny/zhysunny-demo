package com.zhysunny.pattern.behaviour.chainOfResponsibility.mutableChain;

import com.zhysunny.pattern.behaviour.chainOfResponsibility.AbstractExtract;
import com.zhysunny.pattern.behaviour.chainOfResponsibility.impl.CkmExtractChain;
import com.zhysunny.pattern.behaviour.chainOfResponsibility.impl.FilterExtractChain;
import com.zhysunny.pattern.behaviour.chainOfResponsibility.impl.RegexExtractChain;
import com.zhysunny.pattern.behaviour.chainOfResponsibility.impl.TranslateExtractChain;

/**
 * 责任链模式(可变)测试类
 * @author 章云
 * @date 2019/6/18 21:53
 */
public class Main {
    public static void main(String[] args) {
        AbstractExtract filterExtract = new FilterExtractChain();
        AbstractExtract regexExtract = new RegexExtractChain();
        AbstractExtract ckmExtract = new CkmExtractChain();
        AbstractExtract translateExtract = new TranslateExtractChain();
        filterExtract.setNextExtract(regexExtract);
        regexExtract.setNextExtract(ckmExtract);
        ckmExtract.setNextExtract(translateExtract);
        filterExtract.nextExtract();
    }

}
