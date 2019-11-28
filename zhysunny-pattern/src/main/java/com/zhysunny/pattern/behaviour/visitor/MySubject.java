package com.zhysunny.pattern.behaviour.visitor;

/**
 * @author 章云
 * @date 2019/11/28 15:52
 */
public class MySubject implements Subject {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "love";
    }

}
