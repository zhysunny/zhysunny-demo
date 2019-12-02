package com.zhysunny.java.tdd;

/**
 * @author 章云
 * @date 2019/12/2 16:05
 */
public class Args {

    private Schemas schemas;
    private Commands commands;

    public Args(String schema, String command) {
        this.schemas = new Schemas(schema);
        this.commands = new Commands(command);
    }

    public Object getValue(String name) {
        String value = commands.getValue(name);
        return schemas.getValue(name, value);
    }

}
