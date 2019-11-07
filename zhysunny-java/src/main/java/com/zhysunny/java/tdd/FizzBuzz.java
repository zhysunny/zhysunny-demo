package com.zhysunny.java.tdd;

import static java.lang.String.*;

/**
 * FizzBuzz问题：
 * 给你一个整数n. 从 1 到 n 按照下面的规则打印每个数：
 * 如果这个数被3整除，打印fizz.
 * 如果这个数被5整除，打印buzz.
 * 如果这个数能同时被3和5整除，打印fizz buzz.
 * @author 章云
 * @date 2019/10/31 13:55
 */
public class FizzBuzz {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(new FizzBuzz(i));
        }
    }

    private int num;

    public FizzBuzz(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        if (isMultiple(3) && isMultiple(5)) {
            return "FizzBuzz";
        }
        if (isMultiple(3)) {
            return "Fizz";
        }
        if (isMultiple(5)) {
            return "Buzz";
        }
        return valueOf(num);
    }

    private boolean isMultiple(int i) {
        return num % i == 0;
    }

}
