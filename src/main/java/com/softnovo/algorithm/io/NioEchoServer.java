package com.softnovo.algorithm.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioEchoServer {
    public static void main(String[] args) {
        try (Selector selector = Selector.open(); ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind(new InetSocketAddress("127.0.0.1", 1192));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("ServerSocketChannel is open and bound to port 1192");

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                System.out.println("aaaa");

                int channelCount = selector.select();

                if (channelCount == 0) {
                    continue;
                }

                if (channelCount > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            // create clientChannel and register to selector
//                            SocketChannel clientChannel = serverChannel.accept();
//                            clientChannel.configureBlocking(false);
//                            clientChannel.register(selector, SelectionKey.OP_READ);
                            handleAccept(key, selector);
                        } else if (key.isReadable()) {
//                            SocketChannel clientChannel = (SocketChannel) key.channel();
//                            clientChannel.read(buffer);
//                            buffer.flip(); //从"用于读"变为"用于写"
//                            if (buffer.hasRemaining()) { //也可以注册到selector中
//                                buffer.put("_cgm".getBytes());
//                                clientChannel.write(buffer); //echo
//                            }
//                            buffer.clear(); //重复利用
                            handleRead(key);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = socketChannel.read(buffer);
        if (bytesRead == -1) {
            // Client has disconnected
            System.out.println("Client disconnected: " + socketChannel.getRemoteAddress());
            socketChannel.close();
            return;
        }
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        String message = new String(data);
        System.out.println("Received from client " + socketChannel.getRemoteAddress() + ": " + message);
    }

    private static void handleAccept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        if (socketChannel != null) {
            socketChannel.configureBlocking(false);
            // 注册 SocketChannel 到 Selector，关注 READ 事件
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("Client connected: " + socketChannel.getRemoteAddress());
        }
    }
}
