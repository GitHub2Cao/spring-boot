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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexServer {
    private static final int PORT = 1192;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server started on port " + PORT);

            while (true) {
                int readyChannels = selector.select();

                if (readyChannels == 0) {
                    continue;
                }

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    if (key.isAcceptable()) {
                        handleAccept(key, selector);
                    } else if (key.isReadable()) {
                        executorService.submit(() -> handleRead(key));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleAccept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Client connected: " + socketChannel.getRemoteAddress());
    }

    private static void handleRead(SelectionKey key) {
        try {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = socketChannel.read(buffer);
            if (bytesRead == -1) {
                System.out.println("Client disconnected: " + socketChannel.getRemoteAddress());
                socketChannel.close();
                return;
            }
            buffer.flip();
            byte[] data = new byte[buffer.limit()];
            buffer.get(data);
            String message = new String(data);
            // 处理接收到的消息（在这个例子中，将字符串反转）
            String reversedMessage = new StringBuilder(message).reverse().toString();
            // 发送反转后的消息给客户端
            ByteBuffer responseBuffer = ByteBuffer.wrap(reversedMessage.getBytes());
            socketChannel.write(responseBuffer);
            System.out.println("Received from client " + socketChannel.getRemoteAddress() + ": " + message);
            System.out.println("Sent to client " + socketChannel.getRemoteAddress() + ": " + reversedMessage);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
