package com.zero.b;
public class ASingleThreadGate {
    public static void main(String[] args) {
        System.out.println("ctrl + c to exit.");
         
        Gate gate = new Gate();
        new UserThread(gate, "Alice", "Alaska").start();
        new UserThread(gate, "Bobby", "Brazil").start();
        new UserThread(gate, "Chris", "Canada").start();
    }
}
 
class Gate{
    private int count;
    private String name;
    private String address;
     
    public synchronized void pass(String name, String address){
        this.count++;
        this.name=name;
        this.address=address;
         
        check();
    }
     
    public void check() {
            System.out.println("*******clear *******" + toString());
    }
 
    @Override
    public String toString() {
        return "No." + count + " " + name + ", " + address;
    }
     
}
 
class UserThread extends Thread{
    private Gate gate;
    private String name;
    private String address;
     
    public UserThread(Gate gate, String name, String address){
        this.gate=gate;
        this.name=name;
        this.address=address;
    }
 
    @Override
    public void run() {
        System.out.println(name+":begin->");
        while(true){
            gate.pass(name,address);
            Thread.yield();
        }
    }
}