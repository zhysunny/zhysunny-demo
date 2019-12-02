package com.zhysunny.java.tdd;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author 章云
 * @date 2019/12/2 15:14
 */
public class SchemasTest {

    @Test
    public void testBoolean() throws Exception {
        Schemas schemas = new Schemas("l:bool");
        assertEquals(schemas.getValue("l", "true"), Boolean.TRUE);
        assertEquals(schemas.getValue("l", "false"), Boolean.FALSE);
        assertEquals(schemas.getValue("l", ""), Boolean.FALSE);
        assertEquals(schemas.getValue("l", null), Boolean.FALSE);
    }

    @Test
    public void testInt() throws Exception {
        Schemas schemas = new Schemas("i:integer");
        assertEquals(schemas.getValue("i", "1"), 1);
        assertEquals(schemas.getValue("i", "-8"), -8);
        assertEquals(schemas.getValue("i", ""), 0);
        assertEquals(schemas.getValue("i", null), 0);
    }

    @Test
    public void testString() throws Exception {
        Schemas schemas = new Schemas("s:string");
        assertEquals(schemas.getValue("s", "hello"), "hello");
        assertEquals(schemas.getValue("s", ""), "");
        assertEquals(schemas.getValue("s", null), "");
    }

}
