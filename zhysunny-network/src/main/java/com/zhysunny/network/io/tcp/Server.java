package com.zhysunny.network.io.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * TCP服务
 * @author 章云
 * @date 2019/8/19 9:44
 */
public class Server {
    private BufferedReader reader;
    private PrintWriter writer;
    private ServerSocket server;
    private Socket socket;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    private void getServer() {
        try {
            server = new ServerSocket(port);
            System.out.println("服务端已经创建成功");
            System.out.println("等待客户机的连接");
            // 接收连接，阻塞的
            socket = server.accept();
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            String request;
            while ((request = reader.readLine()) != null) {
                System.out.println("客户端信息接收：" + request);
                if ("1".equals(request)) {
                    writer.println("使用功能1");
                } else if ("2".equals(request)) {
                    writer.println("使用功能2");
                } else if ("exit".equals(request)) {
                    writer.println("已退出");
                } else {
                    writer.println(request);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8888);
        server.getServer();
    }
}
