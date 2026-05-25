import java.net.*;
import java.io.*;
import java.lang.*;

public class CodeProducer implements Runnable{

    private SharedVariable var;
    
    public CodeProducer(SharedVariable _var){
        this.var=_var;
    }
    
    public void run(){
        for(int i=0; i<100; i++){
            var.write(i);
        }
    }
}