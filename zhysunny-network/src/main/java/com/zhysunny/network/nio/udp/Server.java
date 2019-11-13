package com.zhysunny.network.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author 章云
 * @date 2019/11/13 21:55
 */
public class Server {

    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        channel.receive(buf);
        String newData = "New String to write to file..."
        + System.currentTimeMillis();

        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();

        int bytesSent = channel.send(buf, new InetSocketAddress("127.0.0.1", 9999));

        channel.connect(new InetSocketAddress("127.0.0.1", 9999));
    }

}
