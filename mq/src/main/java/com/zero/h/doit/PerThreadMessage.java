package com.zero.h.doit;

public class PerThreadMessage {
	public static void main(String[] args) {
		Message msg = new Message();
		msg.sendMsg("操你妈", 1);
		msg.sendMsg("操你妹", 2);
		msg.sendMsg("操你姐", 3);
	}
	

}

class Message{
	private final Holder holder = new Holder();
	private String msg = "";
	private int count ;
	public void sendMsg(final String msg,final int count){
		new Thread(){
			public void run() {
				holder.handleRequestMessage(msg,count);
			};
		}.start();
	}
}

class Holder{
	
	public void handleRequestMessage(String msg,int count){
		for (int i = 0; i <count; i++) {
			System.out.println(Thread.currentThread().getName()+" handle "+ msg);
		}
	
	}
}