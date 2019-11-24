package com.zhysunny.network.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

/**
 * 服务端
 * @author 章云
 * @date 2019/11/24 21:17
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 9999;
        new EchoServer(port).start();
    }

    private void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建ServerBootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 指定使用NIO传输Channel
            bootstrap.group(group).channel(NioServerSocketChannel.class)
            // 使用指定端口设置套接字地址
            .localAddress(new InetSocketAddress(port))
            // 添加一个EchoServerHandler到子Channel的ChannelPipeline
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) {
                    // EchoServerHandler被标注为Sharable，所以我们总是可以使用同样的实例
                    socketChannel.pipeline().addLast(serverHandler);
                }
            });
            // 异步的绑定服务器，调用sync()方法阻塞等待知道绑定完成
            ChannelFuture future = bootstrap.bind().sync();
            // 获取Channel的CloseFuture，并且阻塞当前线程知道它完成
            future.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoopGroup，释放所有资源
            group.shutdownGracefully().sync();
        }
    }

}
