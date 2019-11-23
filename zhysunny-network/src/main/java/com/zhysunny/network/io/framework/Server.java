package com.zhysunny.network.io.framework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 支持并发的RPC服务框架
 * @author 章云
 * @date 2019/11/23 22:41
 */
public class Server {

    private int port;
    private int handle;
    private int maxQueuedCalls;
    private int timeout;
    private boolean running;
    private BlockingQueue<Call> queue = new LinkedBlockingQueue<>();
    private Object lock = new Object();

    public Server(int port, int handle) {
        this.port = port;
        this.handle = handle;
        this.maxQueuedCalls = handle;
        this.timeout = 6000;
    }

    public void start() throws IOException {
        running = true;
        new Listener().start();
        for (int i = 0; i < handle; i++) {
            Handler handler = new Handler(i);
            handler.start();
        }
    }

    public void stop() {
        running = false;
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
        }
        notifyAll();
    }

    /**
     * 等待服务器停止。
     */
    public synchronized void join() throws InterruptedException {
        while (running) {
            wait();
        }
    }

    /**
     * 每个请求任务
     */
    private static class Call {
        private String id;
        private String request;
        private Connection connection;

        public Call(String id, String request, Connection connection) {
            this.id = id;
            this.request = request;
            this.connection = connection;
        }
    }

    /**
     * 监听器，用于监听客户端的连接
     */
    private class Listener extends Thread {
        /**
         * 服务端
         */
        private ServerSocket server;

        /**
         * 创建服务端
         * @throws IOException
         */
        public Listener() throws IOException {
            // 创建服务端
            server = new ServerSocket(port);
            this.setDaemon(true);
            this.setName("Server listener on port " + port);
        }

        @Override
        public void run() {
            System.out.println(getName() + ": starting");
            while (running) {
                try {
                    // 当有一个客户端连接时使用Connection线程处理
                    Socket socket = server.accept();
                    new Connection(socket).start();
                } catch (SocketTimeoutException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + ": exiting");
        }

    }

    /**
     * 处理客户端的请求，并添加到任务队列
     */
    private class Connection extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Connection(Socket socket) throws IOException {
            this.socket = socket;
            // 服务端接收的输入流
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 服务端给客户端的输出流
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.setDaemon(true);
            this.setName("Server connection on port " + port + " from " + socket.getInetAddress().getHostAddress());
        }

        @Override
        public void run() {
            System.out.println(getName() + ": starting");
            try {
                String id = in.readLine();
                System.out.println(getName() + " got #" + id);
                String request = in.readLine();
                Call call = new Call(id, request, this);
                synchronized (queue) {
                    queue.add(call);
                    queue.notify();
                }
                while (running && queue.size() >= maxQueuedCalls) {
                    synchronized (lock) {
                        lock.wait(timeout);
                    }
                }
            } catch (EOFException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void close() {
            try {
                socket.close();
            } catch (IOException e) {
            }
            System.out.println(getName() + ": exiting");
        }

    }

    /**
     * 处理队列的调用。
     */
    private class Handler extends Thread {
        public Handler(int instanceNumber) {
            this.setDaemon(true);
            this.setName("Server handler " + instanceNumber + " on " + port);
        }

        @Override
        public void run() {
            System.out.println(getName() + ": starting");
            while (running) {
                try {
                    Call call;
                    synchronized (queue) {
                        while (running && queue.size() == 0) {
                            queue.wait(timeout);
                        }
                        if (!running) {
                            break;
                        }
                        call = queue.poll();
                    }
                    synchronized (lock) {
                        lock.notify();
                    }
                    System.out
                    .println(getName() + ": has #" + call.id + " from " + call.connection.socket.getInetAddress().getHostAddress());
                    PrintWriter out = call.connection.out;
                    synchronized (out) {
                        out.println(call.id);
                        out.println(call.request);
                        out.flush();
                        call.connection.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(getName() + ": exiting");
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server(8888, 10);
        server.start();
        server.join();
    }

}
