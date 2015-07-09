//package com.zero.g;
//
//import java.util.Random;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReadWriteLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
//public class ReadWriteThreadTest {
//    public static void main(String[] args) {
//        Data data = new Data(5);
//        new ReaderThread(data).start();
//        new ReaderThread(data).start();
//        new ReaderThread(data).start();
//        new ReaderThread(data).start();
//        
//        new WriterThread(data, "asdfghjk").start();
//    }
//}
//
//class Data {
//    private final char[] buffer;
//    private final ReadWriteLock lock = new ReentrantReadWriteLock();
//
//    Lock read = lock.readLock();
//    Lock write = lock.writeLock();
//
//    public Data(int size) {
//        this.buffer = new char[size];
//
//        for (int i = 0; i < buffer.length; i++) {
//            buffer[i] = '*';
//        }
//    }
//
//    public char[] read() {
//        read.lock();
//        try {
//            return doRead();
//        } finally {
//            read.unlock();
//        }
//    }
//
//    public void write(char c) {
//        write.lock();
//        try {
//            doWrite(c);
//        } finally {
//            write.unlock();
//        }
//    }
//
//    private void doWrite(char c) {
//        for (int i = 0; i < buffer.length; i++) {
//            buffer[i] = c;
//
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private char[] doRead() {
//        char[] newbuf = new char[buffer.length];
//        for (int i = 0; i < buffer.length; i++) {
//            newbuf[i] = buffer[i];
//        }
//
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return newbuf;
//    }
//}
//
//class WriterThread extends Thread {
//    private static final Random random = new Random();
//    private final Data data;
//    private final String filter;
//
//    private int index;
//
//    public WriterThread(Data data, String filter) {
//        this.index = 0;
//        this.data = data;
//        this.filter = filter;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                char c = nextchar();
//                data.write(c);
//                Thread.sleep(random.nextInt(3000));
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private char nextchar() {
//        char c = filter.charAt(index);
//        index++;
//        if (index >= filter.length()) {
//            index = 0;
//        }
//        return c;
//    }
//}
//
//class ReaderThread extends Thread{
//    private final Data data;
//    
//    public ReaderThread(Data data) {
//        this.data=data;
//    }
//
//    @Override
//    public void run() {
//        while(true){
//            char[] readbuf = data.read();
//            System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readbuf));
//        }
//    }
//}