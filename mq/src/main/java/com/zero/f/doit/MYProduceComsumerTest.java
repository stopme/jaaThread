package com.zero.f.doit;

import java.util.Random;

public class MYProduceComsumerTest {

	public static void main(String[] args) {
		   MyTable table = new MyTable(3);
	        
	        new Produce("MakerThread1", table , 1000).start();
	        new Produce("MakerThread2", table , 1000).start();
	        new Produce("MakerThread3", table , 1000).start();
	        
	        new TakerThread("TakerThread1", table , 1000).start();
	        new TakerThread("TakerThread2", table , 1000).start();
	        new TakerThread("TakerThread3", table , 1000).start();
	}
}
class Produce extends Thread{
	private final Random random;
	private final MyTable table;
	private static int id;
	public Produce(String name,MyTable table,long seed){
          super(name);
        this.table=table;
        this.random = new Random(seed);
	}

	public static synchronized int nextId(){
        return id++;
    }

	@Override
	public void run() {
		 try{
	            while(true){
	                Thread.sleep(random.nextInt(1000));
	                
	                String cake = "[cake no. " + nextId() + ", by " + getName() + "]";
	                table.put(cake);
	            }
	        }catch(InterruptedException e){
	            e.printStackTrace();
	        }
	}
	
}
class TakerThread extends Thread{
    private final Random random;
    private final MyTable table;
    
    public TakerThread(String name, MyTable table, long seed){
        super(name);
        
        this.table =table;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        try{
            while(true){
                String cake = table.take();
                System.err.println(cake);
                Thread.sleep(random.nextInt(1000));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
class MyTable{
	private String[] cakes;
	private int head=0;
	private int tail=0;
	private int count=0;
	public MyTable(int i){
		cakes = new String[3];
	}
	public synchronized void put(String cake){
		System.out.println(Thread.currentThread().getName() + " puts " + cake);
		if(cakes.length<=count){
			 try {
	                wait();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        
		   cakes[tail] = cake;
	        tail = (tail + 1) % cakes.length;
	        count++;

	        notify();
	}
	public synchronized String take(){
		if(cakes.length <= 0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String cake = cakes[head];
        head = (head + 1) % cakes.length;
        count--;
        notify();
        System.out.println(Thread.currentThread().getName() + " take " + cake);
		return cake;
	}
}