package com.zero.c.doit;

public class FinalThreadImmutable {

	
}

class ImmutablThread implements Runnable{

	private Person p ;
	private String name;
	private String address;
	public ImmutablThread(Person p,String name,String address){
		this.p=p;
		this.name= name;
		this.address = address;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
		}
		
	}
	
}

class Person{
	private final String name;
	private final String address;
	public Person(String name,String address){
		this.name= name;
		this.address = address;
	}
	
	
	public String getName() {
		return name;
	}


	public String getAddress() {
		return address;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[person={name="+this.name+",address="+this.address+"}]";
	}
}
