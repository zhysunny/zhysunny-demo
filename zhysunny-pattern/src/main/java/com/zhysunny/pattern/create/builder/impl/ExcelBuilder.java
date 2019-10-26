package com.zhysunny.pattern.create.builder.impl;

import com.zhysunny.pattern.create.builder.Builder;

import java.util.List;
import java.util.Map;

/**
 * excel输出类
 * @author 章云
 * @date 2019/7/15 11:35
 */
public class ExcelBuilder implements Builder {
    @Override
    public void setFileName(String fileName) {
        System.out.println("文件名：" + fileName + ".xlsx");
    }

    @Override
    public void setTitle(String title) {
        System.out.println("excel标题：" + title);
    }

    @Override
    public void setData(List<Map<String, String>> dataList) {
        System.out.println("开始写入excel数据。。。。。。");
    }
}
