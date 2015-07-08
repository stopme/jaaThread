package com.zero.a;
public class GThreadMutualTest2 {
    public static void main(String[] args) {
        Object obj = new Object();
        GMyThreadMutualA a = new GMyThreadMutualA("nice", obj);
        GMyThreadMutualA b = new GMyThreadMutualA("good", obj);
         
        a.start();
        b.start();
    }
}
 
class GMyThreadMutualA extends Thread {
    private Object obj;
    private String message;
     
    public GMyThreadMutualA(String message, Object obj) {
        this.message=message;
        this.obj=obj;
    }
 
    @Override
    public  void run() {
        synchronized(obj){
            for(int i=1; i<100; i++){
                System.out.println(message);
                 
                if(i%5==0){
                    obj.notify();
                     
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
             
//            obj.notify();
        }
    }
}
 
//class GMyThreadMutualB extends Thread {
// 
//    private Object obj;
//    private String message;
//     
//    public GMyThreadMutualB(String message, Object obj) {
//        this.message=message;
//        this.obj=obj;
//    }
// 
//    @Override
//    public synchronized void run() {
//        synchronized(obj){
//            for(int i=1; i<100; i++){
//                System.out.println(message);
//                if(i%5==0){
//                    obj.notify();
//                     
//                    try {
//                        obj.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//             
//            obj.notify();
//        }
//    }
//}