package com.zhysunny.pattern.behaviour.observer.listener;

import java.util.Map;

/**
 * 监听器测试类(相当于只执行单个观察器)
 * @author 章云
 * @date 2019/6/18 22:24
 */
public class Main {
    public static void main(String[] args) {
        Map<String, String> data = new ListenerMap();
        data.put("name", "zhangyun");
        data.put("name", "zhy");
        data.remove("name");
    }
}
