package com.zero.d.doit;

import java.util.LinkedList;
import java.util.Queue;

public class MyGuardedSuspe {
	public static void main(String[] args) {
		  QueueRequest queueRequest = new QueueRequest();
		    MyClientTread client = new MyClientTread(queueRequest, "alis");
		    MyServerThread server = new MyServerThread(queueRequest, "boddy");
		    client.start();
		    server.start();
	}
  
}

class MyClientTread extends Thread{
	private QueueRequest queueRequest;
	public MyClientTread(QueueRequest queueRequest,String name){
		 super(name);
		this.queueRequest = queueRequest;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			Request e = new Request(i+"");
			queueRequest.setRequest(e);
			System.out.println("client request "+e);
		}
	}
}
class MyServerThread extends Thread{
	private QueueRequest queueRequest;
	public MyServerThread(QueueRequest queueRequest,String name){
		 super(name);
		this.queueRequest = queueRequest;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			Request e = queueRequest.getRequest();
			System.out.println(Thread.currentThread().getName()+" server handles  "+e);
		}
			
	}
}
class QueueRequest{
	private final  Queue<Request> queue = new LinkedList<Request>();
	public synchronized Request getRequest(){
		if(queue.size()<1){
			  try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return queue.poll();
	}
	public synchronized void setRequest(Request e){
		this.queue.offer(e);
		 notifyAll();
	}
}

class Request{
	
	private String name;
	public Request(String name){
		this.name=name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Request name:" + name + "]";
	}
}