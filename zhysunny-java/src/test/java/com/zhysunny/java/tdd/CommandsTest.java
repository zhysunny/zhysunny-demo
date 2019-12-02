package com.zhysunny.java.tdd;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author 章云
 * @date 2019/12/2 17:14
 */
public class CommandsTest {

    @Test
    public void testHasValue() throws Exception {
        Commands commands = new Commands("-l true -s hello");
        assertEquals(commands.getValue("l"), "true");
        assertEquals(commands.getValue("s"), "hello");
    }

    @Test
    public void testHasNoValue() throws Exception {
        Commands commands = new Commands("-l -s hello");
        assertNull(commands.getValue("l"));
        assertEquals(commands.getValue("s"), "hello");
    }

    @Test
    public void testHasNoNegative() throws Exception {
        Commands commands = new Commands("-l -i -9 -s hello");
        assertNull(commands.getValue("l"));
        assertEquals(commands.getValue("i"), "-9");
        assertEquals(commands.getValue("s"), "hello");
    }

}
