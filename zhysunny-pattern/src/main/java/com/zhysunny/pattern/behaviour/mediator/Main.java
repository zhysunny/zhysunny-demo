package com.zhysunny.pattern.behaviour.mediator;

/**
 * @author 章云
 * @date 2019/11/27 16:56
 */
public class Main {

    public static void main(String[] args) {
        Mediator mediator = new MyMediator();
        mediator.createMediator();
        mediator.workAll();
    }

}
