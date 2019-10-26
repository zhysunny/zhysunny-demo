package com.zhysunny.server.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * TCP客户端
 * @author 章云
 * @date 2019/8/19 9:48
 */
public class Client {
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket;

    public void connect() {
        System.out.println("尝试连接");
        try {
            socket = new Socket("127.0.0.1", 8888);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendServerMessage(String message) {
        writer.println(message);
    }

    private void getServerMessage() {
        try {
            while (true) {
                System.out.println(reader.readLine());
            }
        } catch (SocketException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
        Scanner scan = new Scanner(System.in, "UTF-8");
        while (true) {
            String message = scan.next();
            new Thread() {
                @Override
                public void run() {
                    client.sendServerMessage(message);
                    client.getServerMessage();
                }
            }.start();
        }
    }
}
