    import java.lang.*;
    import java.io.*;

    public class TestProdCons{
        public static void main(String[] args){
            try{
                SharedVariable var=new SharedVariable();
                CodeProducer prod=new CodeProducer(var);
                CodeConsumer cons=new CodeConsumer(var);
                Thread []t=new Thread[20];
                for(int i=0; i<10; i++){
                    t[i]=new Thread(prod);
                }
                for(int i=10; i<20; i++){
                    t[i]=new Thread(cons);
                }
                for(int i=0; i<20; i++){
                    t[i].start();
                }
                for(int i=0; i<20; i++){
                    t[i].join();
                }
            }
            catch(Exception e){
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }