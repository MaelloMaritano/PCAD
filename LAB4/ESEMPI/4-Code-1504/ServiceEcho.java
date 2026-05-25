import java.net.*;
import java.io.*;
import java.lang.*;
public class ServiceEcho implements Runnable{
    public Socket socket;

    public ServiceEcho(Socket s){
        this.socket=s;
    }

    public void run(){
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String mess=br.readLine();
            System.out.println("Receive message:"+mess);
            //Thread.sleep(1000);
            pw.println("ECHO "+mess);
            pw.flush();
            br.close();
            pw.close();
            socket.close();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
