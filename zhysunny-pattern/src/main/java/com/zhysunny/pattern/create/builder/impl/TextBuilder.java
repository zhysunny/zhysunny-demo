package com.zhysunny.pattern.create.builder.impl;

import com.zhysunny.pattern.create.builder.Builder;

import java.util.List;
import java.util.Map;

/**
 * txt输出类
 * @author 章云
 * @date 2019/7/15 11:32
 */
public class TextBuilder implements Builder {
    @Override
    public void setFileName(String fileName) {
        System.out.println("文件名：" + fileName + ".txt");
    }

    @Override
    public void setTitle(String title) {
        System.out.println("text标题：" + title);
    }

    @Override
    public void setData(List<Map<String, String>> dataList) {
        System.out.println("开始写入text数据。。。。。。");
    }
}
