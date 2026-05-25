import java.net.*;
import java.io.*;
public class ServerEcho{
    public static void main(String[] args){
        try{
            ServerSocket server=new ServerSocket(4242);
            while(true){
                Socket socket=server.accept();
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
            } }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
