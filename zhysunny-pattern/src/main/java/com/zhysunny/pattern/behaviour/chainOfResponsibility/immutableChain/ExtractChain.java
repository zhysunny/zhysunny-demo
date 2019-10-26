package com.zhysunny.pattern.behaviour.chainOfResponsibility.immutableChain;

import com.zhysunny.pattern.behaviour.chainOfResponsibility.Extract;
import com.zhysunny.pattern.behaviour.chainOfResponsibility.impl.CkmExtractChain;
import com.zhysunny.pattern.behaviour.chainOfResponsibility.impl.FilterExtractChain;
import com.zhysunny.pattern.behaviour.chainOfResponsibility.impl.RegexExtractChain;
import com.zhysunny.pattern.behaviour.chainOfResponsibility.impl.TranslateExtractChain;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链顺序规则
 * @author 章云
 * @date 2019/6/18 21:51
 */
public class ExtractChain implements Extract {

    private List<Extract> chainList = new ArrayList<Extract>(4);

    public ExtractChain() {
        getChain();
    }

    public void getChain() {
        chainList.add(new FilterExtractChain());
        chainList.add(new CkmExtractChain());
        chainList.add(new RegexExtractChain());
        chainList.add(new TranslateExtractChain());
    }

    @Override
    public void extract() {
        for (Extract extract : chainList) {
            extract.extract();
        }
    }

}
