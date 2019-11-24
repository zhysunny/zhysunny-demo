package com.zhysunny.network.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;

/**
 * 客户端
 * @author 章云
 * @date 2019/11/24 21:46
 */
public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient("127.0.0.1", 9999).start();
    }

    private void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
            // 指定使用NIO传输Channel
            .channel(NioSocketChannel.class)
            // 设置服务端地址
            .remoteAddress(new InetSocketAddress(host, port))
            // 添加客户端处理器
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new EchoClientHandler());
                }
            });
            // 连接到远程节点，阻塞等待知道连接完成
            ChannelFuture future = bootstrap.connect().sync();
            // 阻塞，直到Channel关闭
            future.channel().closeFuture().sync();
        } finally {
            // 关闭线程池，释放所有资源
            group.shutdownGracefully().sync();
        }
    }

}
