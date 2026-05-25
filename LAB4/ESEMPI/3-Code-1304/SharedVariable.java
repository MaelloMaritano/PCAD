import java.net.*;
import java.io.*;
import java.lang.*;

public class SharedVariable {
    public int val;
    public boolean readyToWrite;

    public SharedVariable(){
        val=0;
        readyToWrite=true;
    }

    public synchronized  int read(){
        try{
            while(readyToWrite==true){wait();}
            readyToWrite=true;
            notifyAll();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return val;
    }

    public synchronized void write(int v){
        try{
            while(readyToWrite==false){wait();}    
            readyToWrite=false;
            notifyAll();
            val=v;
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
