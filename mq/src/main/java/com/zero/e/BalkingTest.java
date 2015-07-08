package com.zero.e;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class BalkingTest {
    public static void main(String[] args) {
        Data data = new Data("data.txt","(empty)");
         
        new ChangerThread("ChangerThread", data).start();
        new AutoSaverThread("AutoSaverThread", data).start();
    }
}
 
class ChangerThread extends Thread{
    private Data data;
    private Random random = new Random();
     
    public ChangerThread(String name, Data data){
        super(name);
         
        this.data=data;
    }
 
    @Override
    public void run() {
        try{
            for(int i=0; true; i++){
                data.change("No." + i);
                Thread.sleep(random.nextInt(1000));
                data.save();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
 
class AutoSaverThread extends Thread{
    private Data data;
    public AutoSaverThread(String name, Data data){
        super(name);
         
        this.data=data;
    }
    @Override
    public void run() {
        try{
            while(true){
                data.save();
                Thread.sleep(3000);
            }
        }catch(IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
 
class Data{
    private final String filename;
    private String content;
    private boolean changed;
     
    public Data(String filename, String content){
        this.filename=filename;
        this.content=content;
        this.changed = true;
    }
     
    public synchronized void change(String newContent){
        this.content = newContent;
        this.changed=true;
    }
     
    public synchronized void save() throws IOException{
        if(!changed){
            return;
        }
         
        doSave();
        changed=false;
    }
 
    private void doSave() throws IOException {
        System.err.println(Thread.currentThread().getName()+" call doSave(), content = "+ content);
         
        Writer writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }
}