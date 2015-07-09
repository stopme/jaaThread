package com.zero.f.doit;

public class Ticket extends Thread{
public static int count=1000;

public static void main(String[] args) {
	new Ticket().start();
	new Ticket().start();
	new Ticket().start();
}

@Override
	public void run() {
	while(count>0){
		System.err.println(Thread.currentThread().getName()+" "+getTt());
	}
		
		
	}
public synchronized int getTt(){
	count--;
	return count;
}
}
