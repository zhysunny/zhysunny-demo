package com.zhysunny.java.tdd;

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
            System.out.println(getFizzBuzz(i));
        }
    }

    public static String getFizzBuzz(int num) {
        if (num % 15 == 0) {
            return "FizzBuzz";
        } else if (num % 5 == 0) {
            return "Buzz";
        } else if (num % 3 == 0) {
            return "Fizz";
        }
        return num + "";
    }

}
