package com.zhysunny.java.tdd;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 探索区域信息：告知火星车，整片区域的长度（X）和宽度（Y）有多大；
 * 初始化信息：火星车的降落地点（x, y）和朝向（N, S, E, W）信息；
 * 移动指令：火星车可以前进（f）或后退（b）；
 * 转向指令：火星车可以左转90度（l）或右转90度（r）。
 * @author 章云
 * @date 2019/12/2 17:55
 */
public class MarsRoverTest {

    @Test
    public void test() throws Exception {
        // 1.初始化坐标位置和朝向
        // 2.解析指令获得方向和移动距离
        // 3.获得新的坐标
        MarsRover marsRover = new MarsRover(50, 50, 'N');
        // 左转
        MarsRover.Veer veer = new MarsRover.Veer('l');
        // 前进20
        MarsRover.Move move = new MarsRover.Move('f', 20);
        // 执行命令
        marsRover.command(veer, move);
        // 结果是向西前进20
        assertEquals(marsRover.getX(), 30);
        assertEquals(marsRover.getY(), 50);
        assertEquals(marsRover.getToward(), 'W');
    }

    @Test
    public void testVeer() throws Exception {
        // 测试转向
        MarsRover marsRover = new MarsRover(50, 50, 'N');
        marsRover.veer('l');
        assertEquals(marsRover.getToward(), 'W');
        marsRover.veer('r');
        assertEquals(marsRover.getToward(), 'N');
        marsRover.veer('r');
        assertEquals(marsRover.getToward(), 'E');
        marsRover.veer('r');
        assertEquals(marsRover.getToward(), 'S');
        marsRover.veer('r');
        assertEquals(marsRover.getToward(), 'W');
    }

    @Test
    public void testMove() throws Exception {
        // 测试移动
        MarsRover marsRover = new MarsRover(50, 50, 'N');
        // 朝向北
        marsRover.move('f', 10);
        assertEquals(marsRover.getY(), 60);
        marsRover.move('b', 20);
        assertEquals(marsRover.getY(), 40);
        // 朝向东
        marsRover.veer('r');
        marsRover.move('f', 10);
        assertEquals(marsRover.getX(), 60);
        marsRover.move('b', 20);
        assertEquals(marsRover.getX(), 40);
        //测试坐标超出上限
        marsRover.move('b', 190);
        assertEquals(marsRover.getX(), 50);
        marsRover.move('f', 190);
        assertEquals(marsRover.getX(), 40);
    }

}
