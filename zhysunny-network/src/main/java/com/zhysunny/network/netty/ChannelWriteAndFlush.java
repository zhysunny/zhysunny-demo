package com.zhysunny.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 章云
 * @date 2019/11/26 20:35
 */
public class ChannelWriteAndFlush {

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        final Channel channel = new NioServerSocketChannel();
        workerGroup.register(channel);
        // 没有完整的Channel，下面会写入失败
        // 重点是下面写入Channel的过程
        ByteBuf data = Unpooled.copiedBuffer("My data", CharsetUtil.UTF_8);
        ChannelFuture cf = channel.writeAndFlush(data);
        cf.addListener(getChannelFutureListener());
        // 多线程写同一个Channel，Channel是线程安全的
        final ByteBuf buf = Unpooled.copiedBuffer("My data", CharsetUtil.UTF_8).retain();
        Runnable writer = () -> {
            channel.writeAndFlush(buf.duplicate()).addListener(getChannelFutureListener());
        };
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(writer);
        executor.execute(writer);
    }

    private static ChannelFutureListener getChannelFutureListener() {
        return future -> {
            if (future.isSuccess()) {
                System.out.println("Write Success!");
            } else {
                System.out.println("Write Error!");
                future.cause().printStackTrace();
            }
        };
    }

}
