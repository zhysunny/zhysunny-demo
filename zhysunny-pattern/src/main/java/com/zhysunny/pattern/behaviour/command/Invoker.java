package com.zhysunny.pattern.behaviour.command;

/**
 * 抽象调用者，命令的发起者
 * @author 章云
 * @date 2019/11/27 14:21
 */
public abstract class Invoker {

    protected Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public abstract void action();

}
