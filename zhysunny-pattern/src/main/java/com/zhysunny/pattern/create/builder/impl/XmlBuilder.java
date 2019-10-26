package com.zhysunny.pattern.create.builder.impl;

import com.zhysunny.pattern.create.builder.Builder;

import java.util.List;
import java.util.Map;

/**
 * xml输出类
 * @author 章云
 * @date 2019/7/15 11:34
 */
public class XmlBuilder implements Builder {
    @Override
    public void setFileName(String fileName) {
        System.out.println("文件名：" + fileName + ".xml");
    }

    @Override
    public void setTitle(String title) {
        System.out.println("xml标题：" + title);
    }

    @Override
    public void setData(List<Map<String, String>> dataList) {
        System.out.println("开始写入xml数据。。。。。。");
    }
}
