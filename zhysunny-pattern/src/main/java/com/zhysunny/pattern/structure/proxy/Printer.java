package com.zhysunny.pattern.structure.proxy;

/**
 * 可代理的实现类
 * @author 章云
 * @date 2019/6/18 21:58
 */
public class Printer implements Printable {

    private String name;

    public Printer(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void print(String string) {
        System.out.println(name + "打印输出" + string);
    }
}
