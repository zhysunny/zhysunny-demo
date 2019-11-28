package com.zhysunny.pattern.behaviour.memento;

/**
 * @author 章云
 * @date 2019/11/28 14:55
 */
public class Main {

    public static void main(String[] args) {
        // 创建原始类
        Source source = new Source("egg");

        // 创建备忘录，并保存备忘录类
        Storage storage = new Storage(source.createMemento());

        // 修改原始类的状态
        System.out.println("初始化状态为：" + source.getValue());
        source.setValue("niu");
        System.out.println("修改后的状态为：" + source.getValue());

        // 回复原始类的状态
        source.restoreMemento(storage.getMemento());
        System.out.println("恢复后的状态为：" + source.getValue());
    }

}
