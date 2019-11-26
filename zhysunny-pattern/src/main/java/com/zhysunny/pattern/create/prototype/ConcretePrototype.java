package com.zhysunny.pattern.create.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个克隆自身的操作
 * @author 章云
 * @date 2019/11/26 14:56
 */
public class ConcretePrototype extends Prototype {

    private static final long serialVersionUID = 846994832693107997L;

    public ConcretePrototype() {
        setName("Prototype");
        setAge(18);
        List<String> list = new ArrayList<>();
        list.add("hobby1");
        list.add("hobby2");
        setHobby(list);
    }

}
