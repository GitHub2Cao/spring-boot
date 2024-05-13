package com.softnovo.algorithm.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioEchoServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("127.0.0.1", 1192));

        // 异步accept()
        serverChannel.accept(null, new AcceptCompletionHandler(serverChannel));
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
        private AsynchronousServerSocketChannel serverChannel;
        public AcceptCompletionHandler(AsynchronousServerSocketChannel serverChannel) {
            this.serverChannel = serverChannel;
        }

        @Override
        public void completed(AsynchronousSocketChannel clientChannel, Object attachment) {
            serverChannel.accept(attachment, this);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 异步read()
            clientChannel.read(buffer, buffer, new ReadCompletionHandler(clientChannel));
        }

        @Override
        public void failed(Throwable exc, Object attachment) {

        }
    }

    private static class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
        private AsynchronousSocketChannel clientChannel;
        public ReadCompletionHandler(AsynchronousSocketChannel clientChannel) {
            this.clientChannel = clientChannel;
        }

        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            buffer.flip();
            // 异步write()。回调函数为null，写入完成就不用回调了
            clientChannel.write(buffer, null, null); // echo
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {

        }
    }
}
