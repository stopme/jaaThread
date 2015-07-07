package com.zero.a;
public class FMyThreadMutualTest {
    public static void main(String[] args) {
    	FPrintMessage pmsg = new FPrintMessage();
 
        new FMyThreadMutual(pmsg,"good").start();
        new FMyThreadMutual(pmsg,"nice").start();
    }
}
 
class FMyThreadMutual extends Thread {
    private String message;
    private FPrintMessage printMessage;
 
    public FMyThreadMutual(FPrintMessage printMessage,String message) {
        this.printMessage = printMessage;
        this.message=message;
    }
 
    @Override
    public void run() {
        printMessage.show(message);
    }
}
 
class FPrintMessage {
 
    public synchronized void show(String msg) {
        for (int i = 0; i < 10; i++) {
            System.out.println(msg);
            try {
                wait();
                notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}