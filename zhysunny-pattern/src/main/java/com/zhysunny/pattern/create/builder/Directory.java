package com.zhysunny.pattern.create.builder;

import java.util.ArrayList;
import java.util.Map;

/**
 * 用于组装每个建造者
 * @author 章云
 * @date 2019/7/15 11:37
 */
public class Directory {

    private Builder builder;

    public Directory(Builder builder) {
        this.builder = builder;
        this.output();
    }

    /**
     * 用于组装生成文件，这里不同的输出使用相同的数据
     */
    private void output() {
        builder.setFileName("XXX数据");
        builder.setTitle("XXX数据分析报告");
        //这里不放数据了
        builder.setData(new ArrayList<Map<String, String>>());
    }

}
