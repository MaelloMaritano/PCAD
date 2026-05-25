import java.lang.*;
import java.io.*;

public class TestCounter {
    public static void main(String[] args){
        try{
            Counter c=new Counter ();
            CodeCounter code=new CodeCounter(c);
            Thread []t=new Thread[20];
            for(int i=0; i<20; i++){
                t[i]=new Thread(code);
            }
            for(int i=0; i<20; i++){
                t[i].start();
            }
            for(int i=0; i<20; i++){
                t[i].join();
            }
            System.out.println("END");
            System.out.println("The counter's value is "+c.getVal());
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

}