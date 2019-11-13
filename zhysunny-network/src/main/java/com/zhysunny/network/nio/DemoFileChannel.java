package com.zhysunny.network.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author 章云
 * @date 2019/11/13 19:56
 */
public class DemoFileChannel {

    public static void main(String[] args) throws Exception {
        String filepath = Thread.currentThread().getContextClassLoader()
        .getResource("file/input.txt").toString().substring(6);
        File input = new File(filepath);
        // NIO读操作
        RandomAccessFile file = new RandomAccessFile(input, "r");
        FileChannel channel = file.getChannel();
        System.out.println(channel.size());
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Charset charset = Charset.forName("UTF-8");
        int bytesRead = channel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            CharBuffer charBuffer = charset.decode(buf);
            System.out.println(charBuffer.toString());
            buf.clear();
            bytesRead = channel.read(buf);
        }
        channel.close();
        file.close();
        // NIO写操作
        File temp = new File("temp");
        if (!temp.exists()) {
            temp.mkdirs();
        }
        File output = new File(temp, "output.txt");
        if (!output.exists()) {
            output.createNewFile();
        }
        file = new RandomAccessFile(output, "rw");
        channel = file.getChannel();
        System.out.println(channel.size());
        String newData = "这是输出数据... " + System.currentTimeMillis();
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while (buf.hasRemaining()) {
            channel.write(buf);
        }
        channel.close();
        file.close();
    }

}
