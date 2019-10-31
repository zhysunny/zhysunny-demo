package com.zhysunny.java.tdd;

/**
 * 井字游戏
 * 要求在3*3数组中，初始值为+，一人设置为X，一人设置为O，不可重复设置
 * 谁先在横、竖、对角线连起来谁就获胜
 * 需求一、首先设置基本规则：数组长度不能越界，不可重复设置
 * 需求二、设置两个玩家，一个玩家设置后，下一个是另一个玩家
 * 需求三、设置获胜规则
 * 需求四、设置平局
 * @author 章云
 * @date 2019/10/31 14:37
 */
public class Ticktacktoe {

    public static final char PLAY1 = 'X';
    public static final char PLAY2 = 'O';

    private char[][] board;
    private char thisPlayer;
    private char nextPlayer;

    public Ticktacktoe(char play) {
        this.board = new char[][]{ { '+', '+', '+' }, { '+', '+', '+' }, { '+', '+', '+' } };
        this.thisPlayer = play;
    }

    public void play(int x, int y) {
        System.out.println("-----------------------");
        if (x < 1 || x > 3) {
            throw new RuntimeException("X is outside board");
        }
        if (y < 1 || y > 3) {
            throw new RuntimeException("Y is outside board");
        }
        if (board[x - 1][y - 1] == PLAY1 || board[x - 1][y - 1] == PLAY2) {
            throw new RuntimeException("Box is occupied");
        }
        board[x - 1][y - 1] = thisPlayer;
        boolean winner = winner();
        if (winner) {
            System.out.println(thisPlayer + " winner");
            System.out.println("game over");
        } else {
            nextPlayer = thisPlayer == PLAY1 ? PLAY2 : PLAY1;
            thisPlayer = nextPlayer;
            System.out.println("no winner");
        }
        print();
    }

    public boolean winner() {
        // 判断横
        for (int y = 0; y < 3; y++) {
            if (board[0][y] == PLAY1 && board[1][y] == PLAY1 && board[2][y] == PLAY1) {
                return true;
            }
            if (board[0][y] == PLAY2 && board[1][y] == PLAY2 && board[2][y] == PLAY2) {
                return true;
            }
        }
        // 判断竖
        for (int x = 0; x < 3; x++) {
            if (board[x][0] == PLAY1 && board[x][1] == PLAY1 && board[x][2] == PLAY1) {
                return true;
            }
            if (board[x][0] == PLAY2 && board[x][1] == PLAY2 && board[x][2] == PLAY2) {
                return true;
            }
        }
        // 判断对角线
        if (board[0][0] == PLAY1 && board[1][1] == PLAY1 && board[2][2] == PLAY1) {
            return true;
        }
        if (board[0][0] == PLAY2 && board[1][1] == PLAY2 && board[2][2] == PLAY2) {
            return true;
        }
        if (board[2][0] == PLAY1 && board[1][1] == PLAY1 && board[0][2] == PLAY1) {
            return true;
        }
        if (board[2][0] == PLAY2 && board[1][1] == PLAY2 && board[0][2] == PLAY2) {
            return true;
        }
        return false;
    }

    public char getChar(int x, int y) {
        return board[x - 1][y - 1];
    }

    public char getThisPlayer() {
        return thisPlayer;
    }

    public void print() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }
    }

}
