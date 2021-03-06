package com.zhysunny.java.tdd;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author 章云
 * @date 2019/12/2 17:14
 */
public class ArgsTest {

    @Test
    public void testHasValue() throws Exception {
        Args args = new Args("l:bool,s:string,i:int", "-l true -s hello -i 8080");
        assertEquals(args.getValue("l"), Boolean.TRUE);
        assertEquals(args.getValue("s"), "hello");
        assertEquals(args.getValue("i"), 8080);
    }

    @Test
    public void testHasNumber() throws Exception {
        Args args = new Args("l:bool,s:string,i:int", "-l -s hello -i -9");
        assertEquals(args.getValue("l"), Boolean.FALSE);
        assertEquals(args.getValue("s"), "hello");
        assertEquals(args.getValue("i"), -9);
    }

    @Test
    public void testDefault() throws Exception {
        Args args = new Args("l:bool,s:string,i:int", "-l -s -i");
        assertEquals(args.getValue("l"), Boolean.FALSE);
        assertEquals(args.getValue("s"), "");
        assertEquals(args.getValue("i"), 0);
    }

}
