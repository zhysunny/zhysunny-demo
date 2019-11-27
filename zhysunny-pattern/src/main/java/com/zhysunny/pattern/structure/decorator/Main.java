package com.zhysunny.pattern.structure.decorator;

/**
 * @author 章云
 * @date 2019/11/27 10:37
 */
public class Main {

    public static void main(String[] args) {
        Sourceable source = new Source();
        source.method();
        Sourceable decorator = new Decorator(source);
        decorator.method();
    }

}
