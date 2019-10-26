package com.zhysunny.pattern.create.builder;

import com.zhysunny.pattern.create.builder.impl.ExcelBuilder;
import com.zhysunny.pattern.create.builder.impl.TextBuilder;
import com.zhysunny.pattern.create.builder.impl.XmlBuilder;

/**
 * 建造者模式测试类
 * @author 章云
 * @date 2019/7/15 11:42
 */
public class Main {
    public static void main(String[] args) {
        new Directory(new TextBuilder());
        new Directory(new XmlBuilder());
        new Directory(new ExcelBuilder());
    }
}
