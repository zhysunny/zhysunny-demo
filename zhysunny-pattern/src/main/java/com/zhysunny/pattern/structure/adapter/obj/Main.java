package com.zhysunny.pattern.structure.adapter.obj;

import com.zhysunny.pattern.structure.adapter.Source;
import com.zhysunny.pattern.structure.adapter.Targetable;

/**
 * @author 章云
 * @date 2019/11/26 16:10
 */
public class Main {

    public static void main(String[] args) {
        Source source = new Source();
        Targetable adapter = new Adapter(source);
        adapter.method1();
        adapter.method2();
    }

}
