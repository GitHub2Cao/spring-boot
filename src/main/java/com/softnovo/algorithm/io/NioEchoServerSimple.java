package com.softnovo.algorithm.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioEchoServerSimple {
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new java.net.InetSocketAddress(1192));
            serverSocketChannel.configureBlocking(false); // true 是阻塞.
            System.out.println("ServerSocketChannel is open and bound to port 1192");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                // 非阻塞模式下，accept 方法不会阻塞，会立即返回 SocketChannel 或 null
                SocketChannel socketChannel = serverSocketChannel.accept();

                if (socketChannel != null) {
                    System.out.println("Accepted connection from: " + socketChannel.getRemoteAddress());
                    // 处理连接的业务逻辑
                    socketChannel.read(buffer);
                    System.out.println(new String(buffer.array()));
                    buffer.flip();
                    //buffer.put("_cgm_888".getBytes());
                    socketChannel.write(buffer);
                    // ...
                    // 关闭连接
                    socketChannel.close();
                    buffer.clear();
                }
                // 可以进行其他的非阻塞操作
                // ...
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
