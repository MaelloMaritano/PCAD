import java.io.*;
import java.lang.*;

public class CodeCounter implements Runnable{

    private Counter c;
    
    public CodeCounter(Counter _c){
        this.c=_c;
    }
    
    public void run(){
        for(int i=0; i<10000; i++){
            c.increment();
        }
    }
}
