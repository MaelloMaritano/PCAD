import java.lang.*;
import java.io.*;

public class TestThread {
    public static void main(String[] args){
        try{
            ServiceTest st=new ServiceTest();
            Thread t1=new Thread(st,"Bob");
            Thread t2=new Thread(st,"Alice");
            //t1.setDaemon(true);
            //t2.setDaemon(true);
            t1.start();
            t2.start();
            //t1.join();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
