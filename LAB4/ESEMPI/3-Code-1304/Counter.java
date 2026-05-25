public class Counter{

    private int val;
    
    public Counter(){
        val=0;
    }
    
    public int getVal(){
        return val;
    }

    public synchronized void increment(){
        val=val+1;
    }

    public void setVal(int v){
        val=v;
    }
}
