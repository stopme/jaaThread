package com.zero.a;
public class EMyThreadMutualTest {
    public static void main(String[] args) {
        PrintMessage pmsg = new PrintMessage();
 
        new MyThreadMutual(pmsg,"good").start();
        new MyThreadMutual(pmsg,"nice").start();
    }
}
 
class MyThreadMutual extends Thread {
    private String message;
    private PrintMessage printMessage;
 
    public MyThreadMutual(PrintMessage printMessage,String message) {
        this.printMessage = printMessage;
        this.message=message;
    }
 
    @Override
    public void run() {
        printMessage.show(message);
    }
}
 
class PrintMessage {
 
    public synchronized void show(String msg) {
        for (int i = 0; i < 10; i++) {
            System.out.println(msg);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}