/**
 * 
 */
/**
 * @author lizhi
 *
 */
package com.zero.b.doit;
public class OneDoorMain{
	public static void main(String[] args) {
		Door door = new Door();
		UerThread uer1 = new UerThread(door, "AAA", "A@@");
		UerThread uer2 = new UerThread(door, "BBB", "B##");
		uer1.start();
		uer2.start();
	}
	
}
class Door{
	private int count;
	private String name;
	private String address;
	public void pass(String name,String address){
		count++;
		this.name = name;
		this.address = address;
		check();
	}
	private void check(){
		if(this.name.charAt(0) !=this.address.charAt(0)){
			System.out.println(toString());
		}else{
//			System.out.println("*****************************");
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "NO"+this.count+" broken " +this.name+" : "+this.address;
	}
}
class UerThread extends Thread{
	private Door door;
	private String name;
	private String address;
	public UerThread (Door door,String name,String address){
		this.door = door;
		this.name = name;
		this.address = address;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			this.door.pass(name, address);
			   Thread.yield();
		}
	}
}