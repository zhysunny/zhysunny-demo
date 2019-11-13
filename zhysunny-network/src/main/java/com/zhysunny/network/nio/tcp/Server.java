package com.zhysunny.network.nio.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * nio 服务端
 * @author 章云
 * @date 2019/11/13 20:36
 */
public class Server {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    private void getServer() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(this.port));
            System.out.println("服务端已经创建成功");
            ByteBuffer buf = ByteBuffer.allocate(1024);
            Charset charset = Charset.forName("UTF-8");
            SocketChannel channel = null;
            while (true) {
                System.out.println("等待客户机的连接");
                // 接收请求，阻塞的
                channel = serverSocketChannel.accept();
                buf.flip();
                int read = channel.read(buf);
                while (read != -1) {
                    buf.flip();
                    CharBuffer charBuffer = charset.decode(buf);
                    System.out.println(charBuffer.toString());
                    buf.clear();
                    read = channel.read(buf);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(9999);
        server.getServer();
    }

}
