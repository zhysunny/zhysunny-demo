package com.zhysunny.pattern.behaviour.command;

/**
 * @author 章云
 * @date 2019/11/27 14:28
 */
public class Main {

    public static void main(String[] args) {
        Receiver receiver = new Soldiers();
        Command command = new CommandImpl(receiver);
        Invoker invoker = new Commander(command);
        invoker.action();
    }

}
