package com.zero.i;

import java.util.Random;

public class MainClinet {
public static void main(String[] args) {
	WorkPoolManager pool = new WorkPoolManager(5);
	pool.startWork();
	new ClientThread("Alice", pool).start();
    new ClientThread("Bobby", pool).start();
    new ClientThread("Chris", pool).start();
}
}
class WorkPoolManager {
	private int max_length=100;
	private ClientRequest[]  clientRequests ;
	private int head;
	private int tail;
	private int count;
	
	private MyWorkThread[] workerPools;
	public WorkPoolManager(int size){
		 this.clientRequests=new ClientRequest[max_length];
	     this.head = 0;
	     this.tail=0;
	     this.count=0;
	     
	     workerPools = new MyWorkThread[size];
	     
	     for(int i=0;i < workerPools.length;i++){
	    	 workerPools[i] = new MyWorkThread("Worker-" + i, this);
	     }
	}
	
	public void startWork(){
		for (int i = 0; i < workerPools.length; i++) {
			workerPools[i].start();
		}
	}
	public synchronized void put(ClientRequest re){
		
			try {
				while(count >= max_length){
					wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clientRequests[tail]=re;
			tail=(tail+1)%clientRequests.length;
			count++;
		    notify();
	}
	public synchronized ClientRequest take(){
		while(count<=0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ClientRequest request = clientRequests[head];
		 head=(head+1)%clientRequests.length;
	        count--;
	        notify();
	        return request;
	}
}



class MyWorkThread extends Thread{
	private final WorkPoolManager pool;
	public MyWorkThread(String name,WorkPoolManager pool){
		super(name);
		this.pool = pool;
	}
	@Override
	public void run() {
		while (true) {
			ClientRequest re = pool.take();
			 re.excute();
		}
		
	}
}

class ClientThread extends Thread{
	private WorkPoolManager pool;
	 private static final Random random = new Random();
	 public ClientThread(String name,WorkPoolManager pool){
		 super(name);
		 this.pool = pool;
	 }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		 for(int i=0;true; i++){
			ClientRequest re = new ClientRequest(getName(), i);
			pool.put(re);
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


class ClientRequest{
	private final static Random random = new Random();
	private final String name;
	private final int no;
	public ClientRequest(String name,int no){
		this.name = name;
		this.no = no;
	}
	public void excute(){
    System.out.println(Thread.currentThread().getName() + " executes " + this);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		 return "[Request from " + name + " No." + no + "]";
	}
}