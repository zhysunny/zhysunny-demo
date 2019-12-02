package com.zhysunny.java.tdd;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author 章云
 * @date 2019/12/2 17:14
 */
public class Commands {

    Map<String, String> config = new HashMap<>();

    public Commands(String commands) {
        read(commands);
    }

    private void read(String commands) {
        StringTokenizer tokenizer = new StringTokenizer(commands);
        String name = null;
        String value = null;
        while (tokenizer.hasMoreTokens()) {
            if (name == null) {
                name = tokenizer.nextToken();
            }
            if (tokenizer.hasMoreTokens()) {
                value = tokenizer.nextToken();
                if (isName(value)) {
                    name = value;
                    continue;
                }
                config.put(name.substring(1), value);
                name = null;
            }
        }
    }

    private boolean isName(String name) {
        if (null != name && name.length() == 2) {
            if (name.charAt(1) < '0' || name.charAt(1) > '9') {
                return true;
            }
        }
        return false;
    }

    public String getValue(String name) {
        return config.get(name);
    }

}
