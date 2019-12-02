package com.zhysunny.java.tdd;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author 章云
 * @date 2019/12/2 15:37
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
                config.put(name.replaceAll("-", ""), value);
                name = null;
            }
        }
    }

    private boolean isName(String name) {
        if (null != name) {
            if (name.startsWith("-")) {
                String type = name.replaceAll("-", "");
                if (type.length() == 1) {
                    if (type.charAt(0) < '0' || type.charAt(0) > '9') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getValue(String name) {
        return config.get(name);
    }

}
