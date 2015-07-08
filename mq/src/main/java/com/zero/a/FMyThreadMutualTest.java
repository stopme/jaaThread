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
        super.setName(message);
    }
 
    @Override
    public void run() {
    	    printMessage.show("threadName = "+this.getName()+" ## value = "+message);
		}
}
 
class FPrintMessage {
	public String mes ="";
    public synchronized  void show(String msg) {
    	  this.mes =msg;
    		for (int i = 0; i < 100; i++) {
              System.out.println(this.mes);
           }
    	}
    }
