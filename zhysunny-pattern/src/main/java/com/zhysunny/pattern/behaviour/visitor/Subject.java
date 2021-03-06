package com.zhysunny.pattern.behaviour.visitor;

/**
 * @author 章云
 * @date 2019/11/28 15:51
 */
public interface Subject {

    void accept(Visitor visitor);

    String getSubject();

}
