package com.zhysunny.network.io.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.UUID;

/**
 * TCP客户端
 * @author 章云
 * @date 2019/8/19 9:48
 */
public class Client {
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket;

    public void start() {
        try {
            socket = new Socket("127.0.0.1", 8888);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }

    private void sendServerMessage(String message) {
        writer.println(UUID.randomUUID().toString());
        writer.println(message);
    }

    private void getServerMessage() {
        try {
            System.out.println("id=" + reader.readLine());
            System.out.println("response=" + reader.readLine());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in, "UTF-8");
        System.out.println("开始发送消息：");
        while (true) {
            String message = scan.next();
            for (int i = 0; i < 20; i++) {
                Client client = new Client();
                client.start();
                client.sendServerMessage(message + i);
                client.getServerMessage();
                client.stop();
            }
        }
    }
}
