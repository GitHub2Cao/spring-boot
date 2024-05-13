package com.softnovo.algorithm.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.YearMonth;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TestIO {
    public static void main(String[] args) throws IOException, InterruptedException {
//        try (Writer writer = new FileWriter("./cgm.txt")) {
//            String s = "cgm 曹高明";
//            writer.write(s);
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//
//        byte[] source = "学技术信小争哥就对了".getBytes();
//        InputStream in = new ByteArrayInputStream(source);
//        byte[] inBuffer = new byte[source.length];
//        System.out.println(in.read(inBuffer));
//        System.out.println(new String(inBuffer));

//        PipedOutputStream out = new PipedOutputStream();
//        PipedInputStream in = new PipedInputStream(out);
//        new Thread(() -> {
//            try {
//                out.write("Hi cgm~".getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        new Thread(() -> {
//            byte[] buffer = new byte[512];
//            try {
//                in.read(buffer);
//                System.out.println(new String(buffer));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        Thread.sleep(5000);

        // 7845
//        InputStream in = new FileInputStream("./cgm.txt");
//        InputStream bin = new BufferedInputStream(in);
//
//        OutputStream out = new FileOutputStream("./cgm.txt");
//        OutputStream bout = new BufferedOutputStream(out);
//
//        byte[] everyTime = new byte[1024];
//        int count = 0;
//        while (bin.read(everyTime) != -1) {
//            count++;
//            String a = new String(everyTime);
//            System.out.println(a);
////            a = a.replace("\\s+", "");
////            bout.write(a.getBytes());
//        }
//        bout.close();

        OutputStream out = new FileOutputStream("./cgm.txt");
        OutputStreamWriter writer = new OutputStreamWriter(out, "gbk");
        writer.write("l66666000");
//        writer.writeInt(12);
//        writer.writeChar('a');
//        writer.writeFloat(12.12f);
//        out.close();
        writer.close();
        out.close();

        InputStreamReader in = new InputStreamReader(new FileInputStream("./cgm.txt"));
        char[] buffer = new char[1024];
        System.out.println(in.read(buffer));
        System.out.println(new String(buffer));

        in.close();
    }





}
