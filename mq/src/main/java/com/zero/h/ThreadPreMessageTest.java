package com.zero.h;
public class ThreadPreMessageTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("main begin!");
        Host host = new Host();
        host.request(1, 'A');
        host.request(2, 'B');
        host.request(3, 'C');
        System.out.println("main end!");
    }
}

class Host{
    private final Helper helper = new Helper();
    public void request(final int count, final char c){
        System.out.println("    request(" + count + ", " + c + ") begin");
        
        new Thread(){
            public void run(){
                helper.handle(count, c);
            }
        }.start();
        
        System.out.println("    request(" +count + ", " + c + ")end");
    }
}

class Helper {
    public void handle(int count, char c) {
        System.out.println("    handle(" + count + ", " + c + ")begin");

        for (int i = 0; i < count; i++) {
            System.out.println(c);
        }

        System.out.println("    handle(" + count + ", " + c + ")end");
    }
}