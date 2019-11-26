package com.zhysunny.pattern.structure.bridge;

/**
 * @author 章云
 * @date 2019/11/26 16:34
 */
public class SourceSub1 implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is the first sub!");
    }

}
