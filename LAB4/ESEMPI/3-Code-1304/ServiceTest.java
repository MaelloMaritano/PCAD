import java.lang.*;
import java.io.*;
public class ServiceTest implements Runnable {
    public void run(){
        try{
            while(true){
                Thread.sleep(1000);
                System.out.println("Hello "+Thread.currentThread().getName());
            }
            
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
