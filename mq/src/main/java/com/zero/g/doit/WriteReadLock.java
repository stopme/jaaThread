package com.zero.g.doit;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteReadLock {
	public static void main(String[] args) {
		Dataa data = new Dataa(6);
		new ReadThread(data).start();
		new ReadThread(data).start();
		new ReadThread(data).start();
		new WriteThread(data, "adfgkjh");
	}

}

class Dataa{
	private final char[] buffer;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	  Lock readLock = lock.readLock();
	  Lock writeLock = lock.writeLock();
	public Dataa(int size){
		buffer = new char[size];
		for (int i = 0; i < size; i++) {
			buffer[i]='*';
		}
	}
	public char[] read(){
		readLock.lock();
		 try {
	            return doRead();
	        } finally {
	        	readLock.unlock();
	        }
	}
	private char[]  doRead(){
		char[] newBuffer = new char[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			  newBuffer[i]= buffer[i];
		}
		  try {
	            Thread.sleep(50);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		return newBuffer;
	}
	public void write(char c){
		writeLock.lock();
		try{
		doWrite(c);
		}finally{
			writeLock.unlock();
		}
		
	}
	private void doWrite(char c){
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = c;
			 try {
	                Thread.sleep(50);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
		}
	}
}

class WriteThread extends Thread{
	 private static final Random random = new Random();
	private Dataa data;
	private String filter;
	private int id=0;
	public WriteThread(Dataa data,String filter){
		this.data = data;
		this.filter=filter;
	}
	@Override
	public void run() {
		 try {
	            while (true) {
	                char c = nextChar();
	                data.write(c);
	                Thread.sleep(random.nextInt(3000));
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	}
	public char nextChar(){
		
	char c =	filter.charAt(id);
		id++;
		if(id >= filter.length()){
			id=0;
		}
		return c;
	}

}
class ReadThread extends Thread{

	private Dataa data;
	public ReadThread(Dataa data){
		this.data = data;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			char[] c= data.read();
			System.out.println(Thread.currentThread().getName()+" read "+String.valueOf(c));
		}
	}
}