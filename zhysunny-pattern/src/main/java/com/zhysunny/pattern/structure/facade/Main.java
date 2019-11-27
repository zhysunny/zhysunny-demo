package com.zhysunny.pattern.structure.facade;

/**
 * @author 章云
 * @date 2019/11/27 13:51
 */
public class Main {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }

}
