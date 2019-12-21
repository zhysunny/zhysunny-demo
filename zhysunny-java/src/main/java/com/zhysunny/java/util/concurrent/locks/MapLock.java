package com.zhysunny.java.util.concurrent.locks;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟读写功能
 * @author 章云
 * @date 2019/12/20 14:08
 */
public interface MapLock {

    Map<String, String> MAP = new HashMap<>();

    /**
     * 读
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 写
     * @param key
     * @param value
     */
    void put(String key, String value);

    /**
     * 集合长度
     * @return
     */
    default int size() {
        return MAP.size();
    }

    default void clear(){
        MAP.clear();
    }

    default String string(){
        return MAP.toString();
    }

}
