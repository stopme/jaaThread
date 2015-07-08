/**
 * 
 */
/**
 * @author lizhi
 *
 */
package com.zero.e.doit;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class MYBalkingTest{
	public static void main(String[] args) {
		  MyData data = new MyData("data.txt");
	     
	     new MyChangeThread(data,"MyChangeThread").start();
	     new MyAutoSaveThread(data,"MyAutoSaveThread").start();
	}
	
}
class MyChangeThread extends Thread{
	private MyData data;
	private Random random = new Random();
	public MyChangeThread(MyData data ,String name){
		super(name);
		this.data = data;
	}
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			data.change("change "+" * "+i);
			try {
				Thread.sleep(random.nextInt(1000));
				 data.save();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		}
	}
	
}
class MyAutoSaveThread extends Thread{
	private MyData data;
	public MyAutoSaveThread(MyData data ,String name){
		super(name);
		this.data = data;
	}
	@Override
	public void run() {
		while(true){
			try {
				  data.save();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
}
//class MyConsumerSaveThread extends Thread{
//
//	private MyData data;
//	public MyConsumerSaveThread(MyData data ){
//		this.data = data;
//	}
//	@Override
//	public void run() {
//		
//		try {
//		data.save();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//		}
//}
class MyData{
	private final String fileName;
	private boolean changed = false;
	private String content;
	public MyData(String fileName){
		this.fileName = fileName;
	}
	private void doSave() throws IOException{
		 System.out.println(Thread.currentThread().getName()+" call doSave(), content = "+ content);
	        Writer writer = new FileWriter(fileName);
	        writer.write(content);
	        writer.close();
	}
	public synchronized void save() throws IOException{
		if(changed){
			doSave();
			changed=false;
		}
	}
	public synchronized void change(String newContent){
		content= newContent;
		changed=true;
	}
}
