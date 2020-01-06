package com.zhysunny.java.util.event;

/**
 * 事件机制，观察者模式
 * @author 章云
 * @date 2020/1/6 10:52
 */
public class Main {

    public static void main(String[] args) {
        MyEventSource source = new MyEventSource();
        //注册监听器
        source.addCusListener(new MyEventListener() {
            @Override
            public void fireMyEvent(MyEvent event) {
                super.fireMyEvent(event);
            }
        });
        //触发事件
        source.setName("eric");
    }

}
