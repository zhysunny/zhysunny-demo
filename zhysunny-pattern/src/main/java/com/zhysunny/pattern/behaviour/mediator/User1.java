package com.zhysunny.pattern.behaviour.mediator;

/**
 * @author 章云
 * @date 2019/11/27 15:00
 */
public class User1 extends User {

    public User1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user1 exe!");
    }

}
