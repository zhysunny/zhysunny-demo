package com.zhysunny.pattern.structure.bridge;

/**
 * 桥接类
 * @author 章云
 * @date 2019/11/26 16:34
 */
public abstract class Bridge {

    private Sourceable source;

    public Sourceable getSource() {
        return source;
    }

    public void setSource(Sourceable source) {
        this.source = source;
    }

    public void method() {
        source.method();
    }

}
