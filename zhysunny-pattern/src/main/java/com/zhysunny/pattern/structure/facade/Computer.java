package com.zhysunny.pattern.structure.facade;

/**
 * 外观类
 * @author 章云
 * @date 2019/11/27 13:47
 */
public class Computer {

    private CPU cpu;
    private Memory memory;
    private Disk disk;

    public Computer() {
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }

    public void startup() {
        System.out.println("Computer startup !");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("Computer startup finished !");
    }

    public void shutdown() {
        System.out.println("Computer shutdown !");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("Computer shutdown finished !");
    }

}
