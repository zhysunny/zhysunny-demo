package com.zhysunny.pattern.behaviour.command;

/**
 * 抽象的命令
 * @author 章云
 * @date 2019/11/27 14:19
 */
public abstract class Command {

    protected Receiver receiver;

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    public abstract void execute();

}
