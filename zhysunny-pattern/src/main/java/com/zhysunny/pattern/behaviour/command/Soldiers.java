package com.zhysunny.pattern.behaviour.command;

/**
 * 士兵
 * @author 章云
 * @date 2019/11/27 14:27
 */
public class Soldiers implements Receiver {

    @Override
    public void action() {
        System.out.println("command received!");
    }

}
