package com.zero.mq;

import java.util.LinkedList;
import java.util.Queue;

public class MqMassage {

	public static Queue<String> queue =  new LinkedList<String>();
	public int add(String s){
		 queue.add(s);
		 return queue.size();
	}
	public String get(){
		return  queue.peek();
	}
}
