package com.softnovo.algorithm.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class TestChannel {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("./cgm.txt", "rw");
        FileChannel channel = file.getChannel();
       // FileChannel channel = FileChannel.open(Paths.get("./cgm.txt"));
        ByteBuffer buffer = ByteBuffer.allocate(512);
        while (channel.read(buffer) != -1) {
            // 处理buffer中的数据data
            System.out.println(new String(buffer.array()));
        }

        String data = "Hello, World!11";
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

        // 将数据写入文件通道
        channel.write(java.nio.ByteBuffer.wrap(bytes));

        // 关闭文件通道
        channel.close();
    }
}
