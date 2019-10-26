package com.zhysunny.server.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * TCP服务
 * @author 章云
 * @date 2019/8/19 9:44
 */
public class Server {
    static final int INPORT = 8888;
    private byte[] buf = new byte[10240];
    private DatagramPacket dp = new DatagramPacket(buf, buf.length);
    private DatagramSocket socket;

    public Server() {
        try {
            // 创建一接收消息的对象，而不是每次接收消息都创建一个
            socket = new DatagramSocket(INPORT);
            System.out.println("Server started");
            while (true) {
                socket.receive(dp);
                //接收到客户端的消息
                String rcvd = Packet.toString(dp) + ",from address:" + dp.getAddress() + ",port:" + dp.getPort();
                System.out.println("From Client:" + rcvd);
                String echoString = "From Server Echoed:" + rcvd;
                DatagramPacket echo = Packet.toDatagram(echoString, dp.getAddress(), dp.getPort());
                //将数据包发送给客户端
                socket.send(echo);
            }
        } catch (SocketException e) {
            System.err.println("Can't open socket");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Communication error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
