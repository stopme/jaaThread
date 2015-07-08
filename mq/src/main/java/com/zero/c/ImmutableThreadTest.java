package com.zero.c;
public class ImmutableThreadTest {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        Person p = new Person("Alice", "Alaska");
        new PrintPersonThread(p).start();
        new PrintPersonThread(p).start();
    }
}
 
class PrintPersonThread extends Thread{
    private Person person;
    public PrintPersonThread(Person person){
        this.person = person;
    }
    @Override
    public void run() {
        while(true){
            System.out.println(Thread.currentThread().getName() + " Prints " + person);
        }
    }
}
 
final class  Person{
    private final String name;
    private final String address;
     
    public Person(String name, String address){
        this.name=name;
        this.address=address;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    @Override
    public String toString() {
        return "[person:name=" + name + ", address=" + address + "]";
    }
}