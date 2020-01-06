package com.zhysunny.java.util.event;

import java.util.EventObject;

/**
 * 事件类,用于封装事件源及一些与事件相关的参数.
 * @author 章云
 * @date 2020/1/6 10:29
 */
public class MyEvent extends EventObject {

    private static final long serialVersionUID = -5209925462736058589L;

    /**
     * Constructs a prototypical MyEvent.
     * @param source 事件源
     * @throws IllegalArgumentException if source is null.
     */
    public MyEvent(Object source) {
        super(source);
    }

}
