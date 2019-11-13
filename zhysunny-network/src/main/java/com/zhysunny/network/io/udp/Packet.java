package com.zhysunny.network.io.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * udp数据的发送和接收
 * @author 章云
 * @date 2019/8/19 11:50
 */
public class Packet {
    public static DatagramPacket toDatagram(String s, InetAddress destIA, int destPort) {
        return new DatagramPacket(s.getBytes(), s.getBytes().length, destIA, destPort);
    }

    public static String toString(DatagramPacket p) {
        return new String(p.getData(), 0, p.getLength());
    }
}
