package com.zhysunny.java.util.event;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 事件源。事件发生的地方，由于事件源的某项属性或状态发生了改变(比如BUTTON被单击、TEXTBOX的值发生改变等等)导致某项事件发生。换句话说就是生成了相应的事件对象。因为事件监听器要注册在事件源上,所以事件源类中应该要有盛装监听器的容器(List,Set等等)。
 * @author 章云
 * @date 2020/1/6 10:40
 */
public class MyEventSource {

    private String name;
    /**
     * 监听器容器
     */
    private Set listener;

    public MyEventSource() {
        this.listener = new HashSet();
        this.name = "default_name";
    }

    /**
     * 给事件源注册监听器
     * @param listener
     */
    public void addCusListener(MyEventListener listener) {
        this.listener.add(listener);
    }

    /**
     * 当事件发生时,通知注册在该事件源上的所有监听器做出相应的反应（调用回调方法）
     */
    protected void notifies() {
        MyEventListener listener = null;
        Iterator<MyEventListener> iterator = this.listener.iterator();
        while (iterator.hasNext()) {
            listener = iterator.next();
            listener.fireMyEvent(new MyEvent(this));
        }
    }

    public String getName() {
        return name;
    }

    /**
     * 模拟事件触发器，当成员变量name的值发生变化时，触发事件。
     * @param name
     */
    public void setName(String name) {
        if (!this.name.equals(name)) {
            this.name = name;
            notifies();
        }
    }

}
