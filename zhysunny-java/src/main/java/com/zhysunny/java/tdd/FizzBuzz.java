package com.zhysunny.java.tdd;

/**
 * @author 章云
 * @date 2019/11/6 8:59
 */
public class FizzBuzz {

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            System.out.println(getFizzBuzz(i));
        }
    }

    public static String getFizzBuzz(int i) {
        StringBuilder sb = new StringBuilder();
        if (i % 3 == 0) {
            sb.append("Fizz");
        }
        if (i % 5 == 0) {
            sb.append("Buzz");
        }
        if (i % 3 != 0 && i % 5 != 0) {
            sb.append(i);
        }
        return sb.toString();
    }

}
