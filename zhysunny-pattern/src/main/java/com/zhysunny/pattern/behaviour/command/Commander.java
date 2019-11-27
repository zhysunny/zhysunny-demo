package com.zhysunny.pattern.behaviour.command;

/**
 * 司令
 * @author 章云
 * @date 2019/11/27 14:25
 */
public class Commander extends Invoker {

    public Commander(Command command) {
        super(command);
    }

    @Override
    public void action() {
        command.execute();
    }

}
