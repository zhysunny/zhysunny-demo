package com.zhysunny.java.tdd;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 章云
 * @date 2019/12/2 18:27
 */
public class MarsRover {

    private static final int X = 100;
    private static final int Y = 100;
    private static final char N = 'N'; //北
    private static final char S = 'S'; //南
    private static final char W = 'W'; //西
    private static final char E = 'E'; //东
    private static final List<Character> towards = new ArrayList<>();

    static {
        towards.add(N);
        towards.add(E);
        towards.add(S);
        towards.add(W);
    }

    private int x;
    private int y;
    private char toward;
    private int index;

    public MarsRover(int x, int y, char toward) {
        this.x = x;
        this.y = y;
        this.toward = toward;
        this.index = towards.indexOf(toward);
    }

    @Override
    public String toString() {
        return "MarsRover{" +
        "x=" + x +
        ", y=" + y +
        ", toward=" + toward +
        '}';
    }

    /**
     * 转向
     * @param cmd 左转90度（l）或右转90度（r）
     * @return
     */
    public void veer(char cmd) {
        if (cmd == 'l') {
            // towards存储顺序是顺时针，向左为逆时针
            this.index--;
        } else if (cmd == 'r') {
            // towards存储顺序是顺时针，向右为顺时针
            this.index++;
        }
        if (this.index == -1) {
            this.index = towards.size() - 1;
        }
        if (this.index == towards.size()) {
            this.index = 0;
        }
        this.toward = towards.get(this.index);
    }

    /**
     * 移动
     * @param cmd      前进（f）或后退（b）
     * @param distance 移动距离
     */
    public void move(char cmd, int distance) {
        if (this.toward == 'N') {
            if (cmd == 'f') {
                this.y += distance;
            }
            if (cmd == 'b') {
                this.y -= distance;
            }
        }
        if (this.toward == 'E') {
            if (cmd == 'f') {
                this.x += distance;
            }
            if (cmd == 'b') {
                this.x -= distance;
            }
        }
        if (this.toward == 'S') {
            if (cmd == 'f') {
                this.y -= distance;
            }
            if (cmd == 'b') {
                this.y += distance;
            }
        }
        if (this.toward == 'W') {
            if (cmd == 'f') {
                this.x -= distance;
            }
            if (cmd == 'b') {
                this.x += distance;
            }
        }
        while (this.x > X) {
            this.x -= X;
        }
        while (this.x < 0) {
            this.x += X;
        }
        while (this.y > Y) {
            this.y -= Y;
        }
        while (this.y < 0) {
            this.y += Y;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getToward() {
        return toward;
    }

}
