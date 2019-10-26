package com.zhysunny.pattern.create.factoryMethod;

/**
 * 工厂接口
 * @author 章云
 * @date 2019/5/13 15:49
 */
public interface Factory {

    /**
     * 工厂创建类方法
     * @return
     */
    Sender create();

}
