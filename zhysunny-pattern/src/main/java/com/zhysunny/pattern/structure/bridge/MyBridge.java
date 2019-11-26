package com.zhysunny.pattern.structure.bridge;

/**
 * @author 章云
 * @date 2019/11/26 16:37
 */
public class MyBridge extends Bridge {

    @Override
    public void method() {
        getSource().method();
    }

}
