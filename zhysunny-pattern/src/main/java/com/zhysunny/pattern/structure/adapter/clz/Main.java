package com.zhysunny.pattern.structure.adapter.clz;

import com.zhysunny.pattern.structure.adapter.Targetable;

/**
 * @author 章云
 * @date 2019/11/26 16:10
 */
public class Main {

    public static void main(String[] args) {
        Targetable adapter = new Adapter();
        adapter.method1();
        adapter.method2();
    }

}
