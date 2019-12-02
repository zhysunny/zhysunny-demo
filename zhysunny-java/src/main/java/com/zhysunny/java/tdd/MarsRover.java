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

    /**
     * 执行命令
     * @param commands
     */
    public void command(Command... commands) {
        for (Command command : commands) {
            if (command instanceof Veer) {
                Veer veer = (Veer)command;
                this.veer(veer.getCmd());
            } else if (command instanceof Move) {
                Move move = (Move)command;
                this.move(move.getCmd(), move.getDistance());
            }
        }
    }

    public interface Command {
    }

    public static class Veer implements Command {
        private char cmd;

        public Veer(char cmd) {
            this.cmd = cmd;
        }

        public char getCmd() {
            return cmd;
        }
    }

    public static class Move implements Command {
        private char cmd;
        private int distance;

        public Move(char cmd, int distance) {
            this.cmd = cmd;
            this.distance = distance;
        }

        public char getCmd() {
            return cmd;
        }

        public int getDistance() {
            return distance;
        }
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
            this.index = this.index == -1 ? towards.size() - 1 : this.index;
        } else if (cmd == 'r') {
            // towards存储顺序是顺时针，向右为顺时针
            this.index++;
            this.index = this.index == towards.size() ? 0 : this.index;
        }
        this.toward = towards.get(this.index);
    }

    /**
     * 移动
     * @param cmd      前进（f）或后退（b）
     * @param distance 移动距离
     */
    public void move(char cmd, int distance) {
        if ((this.toward == 'N' && cmd == 'f') || (this.toward == 'S' && cmd == 'b')) {
            this.y = (this.y + distance) % Y;
        } else if ((this.toward == 'N' && cmd == 'b') || (this.toward == 'S' && cmd == 'f')) {
            this.y = ((this.y - distance) % Y + Y) % Y;
        }
        if ((this.toward == 'E' && cmd == 'f') || (this.toward == 'W' && cmd == 'b')) {
            this.x = (this.x + distance) % X;
        } else if ((this.toward == 'E' && cmd == 'b') || (this.toward == 'W' && cmd == 'f')) {
            this.x = ((this.x - distance) % X + X) % X;
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
