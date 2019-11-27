package com.zhysunny.pattern.behaviour.mediator;

/**
 * @author 章云
 * @date 2019/11/27 14:59
 */
public abstract class User {

    private Mediator mediator;

    public Mediator getMediator() {
        return mediator;
    }

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void work();

}
