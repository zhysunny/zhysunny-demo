package com.zhysunny.pattern.structure.adapter.inter;

import com.zhysunny.pattern.structure.adapter.Targetable;

/**
 * @author 章云
 * @date 2019/11/26 16:10
 */
public class Main {

    public static void main(String[] args) {
        Targetable sub1 = new SourceSub1();
        sub1.method1();
        sub1.method2();
        Targetable sub2 = new SourceSub2();
        sub2.method1();
        sub2.method2();
    }

}
