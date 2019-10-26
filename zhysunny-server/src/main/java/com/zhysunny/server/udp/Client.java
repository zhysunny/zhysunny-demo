package com.zhysunny.server.udp;

import java.io.IOException;
import java.net.*;

/**
 * TCP客户端
 * @author 章云
 * @date 2019/8/19 9:48
 */
public class Client extends Thread {
    private DatagramSocket socket;
    private InetAddress hostAddress;
    private byte[] buf = new byte[10240];
    private DatagramPacket dp = new DatagramPacket(buf, buf.length);
    private int id;

    public Client(int identifier) {
        id = identifier;
        try {
            socket = new DatagramSocket();
            hostAddress = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            System.err.println("Cannot find host");
            System.exit(1);
        } catch (SocketException e) {
            System.err.println("Can't open socket");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Client starting");
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1; i++) {
                String outMessage = "Client #" + id + ",message #" + i;
                socket.send(Packet.toDatagram(outMessage, hostAddress, 8888));
                socket.receive(dp);
                String rcvd = "Client #" + id + ",rcvd from " + dp.getAddress() + ", " + dp.getPort() + ":" + Packet.toString(dp);
                System.out.println(rcvd);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            new Client(i).start();
        }
    }
}
