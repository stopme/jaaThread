/**
 * 
 */
/**
 * @author lizhi
 *
 */
package com.zero.a.doit;

public class MythreadTest{
	
	public static void main(String[] args) {
		OneRunable thread1 = new OneRunable("one111111111");
		OneRunable thread2 = new OneRunable("one2222222");
		    new Thread(thread1).start();
	        new Thread(thread2).start();
	        TwoThread twoThread1 = new TwoThread("two111111111");
	        TwoThread twoThread2 = new TwoThread("two2222222");
	        twoThread1.start();
	        twoThread2.start();
	}
}

class OneRunable implements Runnable{
	public String name ="";
	public OneRunable(String name){
		  this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("threadName= "+this.name);
		}
		
	}
}

class TwoThread extends Thread{
	public String name ="";
	public TwoThread(String name){
		  this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("threadName= "+this.name);
		}
		
      }
	}