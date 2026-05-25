import java.net.*;
import java.io.*;
import java.lang.*;

public class CodeConsumer implements Runnable{

    private SharedVariable var;
    
    public CodeConsumer(SharedVariable _var){
        this.var=_var;
    }
    
    public void run(){
        for(int i=0; i<100; i++){
            System.out.println(var.read());
        }
    }
}