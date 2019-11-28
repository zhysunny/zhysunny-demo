package com.zhysunny.pattern.behaviour.visitor;

/**
 * @author 章云
 * @date 2019/11/28 15:52
 */
public class MyVisitor implements Visitor {

    @Override
    public void visit(Subject sub) {
        System.out.println("visit the subject：" + sub.getSubject());
    }

}
