package com.softnovo.algorithm.juc;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
    public static class DataBlock {
        private final byte[] bytes;

        public DataBlock(int byteCount) {
            bytes = new byte[byteCount];
        }

        public String toString() {
            return "DataBlock(byteCount=" + bytes.length + ")";
        }
    }

    public static void main(String[] args) {
        // 1024*1024*10字节是10MB
        DataBlock dataBlock = new DataBlock(1024 * 1024 * 5);
        SoftReference<DataBlock> softReference = new SoftReference<>(dataBlock);
        System.out.println("通过软引用访问到的对象：" + softReference.get());
        System.gc();
        dataBlock = null;
        byte[] anotherByteArray = new byte[1024 * 1024 * 5];
        System.out.println("通过软引用访问到的对象：" + softReference.get());
    }
}
