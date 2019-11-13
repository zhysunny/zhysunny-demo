package com.zhysunny.network.nio.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * nio 客户端
 * @author 章云
 * @date 2019/11/13 20:42
 */
public class Client {

    private SocketChannel channel;

    public void connect(String hostname, int port) {
        System.out.println("尝试连接");
        try {
            channel = SocketChannel.open();
            channel.connect(new InetSocketAddress(hostname, port));
            System.out.println("连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendServerMessage(String message) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();
        buf.put(message.getBytes());
        buf.flip();
        channel.write(buf);
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connect("127.0.0.1", 9999);
        Scanner scan = new Scanner(System.in, "UTF-8");
        while (true) {
            String message = scan.next();
            new Thread(() -> {
                try {
                    client.sendServerMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
