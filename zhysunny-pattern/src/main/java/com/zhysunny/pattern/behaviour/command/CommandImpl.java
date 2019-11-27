package com.zhysunny.pattern.behaviour.command;

/**
 * 命令的实现
 * @author 章云
 * @date 2019/11/27 14:22
 */
public class CommandImpl extends Command {

    public CommandImpl(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        receiver.action();
    }

}
