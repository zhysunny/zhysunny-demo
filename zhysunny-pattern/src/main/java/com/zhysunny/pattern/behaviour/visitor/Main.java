package com.zhysunny.pattern.behaviour.visitor;

/**
 * @author 章云
 * @date 2019/11/28 15:53
 */
public class Main {

    public static void main(String[] args) {
        Visitor visitor = new MyVisitor();
        Subject sub = new MySubject();
        sub.accept(visitor);
    }

}
