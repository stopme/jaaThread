package com.zero.d;

import java.util.LinkedList;

public class GuardedSuspensionTest {
    public static void main(String[] args) {
        RequestQueue queue = new RequestQueue();
        ClientThread ct = new ClientThread(queue, "Alice's Client");
        ServerThread st = new ServerThread(queue, "Bobby's Server");
         
        ct.start();
        st.start();
    }
}
 
class ServerThread extends Thread{
    private RequestQueue requestQueue;
     
    public ServerThread(RequestQueue requestQueue, String name){
        super(name);
         
        this.requestQueue=requestQueue;
    }
 
    @Override
    public void run() {
        for(int i=0;i <10;i++){
            Request request=requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName() + " handles " + request);
            Thread.yield();
        }
    }
}
 
class ClientThread extends Thread{
    private RequestQueue requestQueue;
     
    public ClientThread(RequestQueue requestQueue, String name){
        super(name);
         
        this.requestQueue=requestQueue;
    }
 
    @Override
    public void run() {
        for(int i=0; i<10;i++){
            Request request = new Request("No." + i);
            System.out.println(Thread.currentThread().getName() + " requests " + request);
            requestQueue.putRequest(request);
             
            Thread.yield();
        }
    }
}
 
class RequestQueue{
 
    private final LinkedList<Request> queue = new LinkedList<Request>();
 
    public synchronized Request getRequest(){
        while(queue.size()<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         
        return queue.removeFirst();
    }
     
    public synchronized void putRequest(Request request){
        queue.addLast(request);
        notifyAll();
    }
}
 
class Request{
    private final String name;
    public Request(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
 
    @Override
    public String toString() {
        return "[Request name:" + name + "]";
    }
}