package com.zhysunny.java;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        String[] array = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(reverse(array[i])).append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());
        System.out.println(sb.toString());
    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
