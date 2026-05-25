package ProdConsServer;

import java.io.*;
import java.net.*;

public class ConsumerClient
{
	public static void main(String[] args)
	{
		try
		{
			Socket socket=new Socket("localhost",4242);
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			pw.println("consumer");
			pw.flush();
			System.out.println("Consumer tried to connect");
			String mess=br.readLine();
			System.out.println("Recieved "+mess);
			if(mess.equals("okcons"))
			{
				mess=br.readLine();
				System.out.println("Consumer read: "+mess);
			}
			pw.close();
			br.close();
			socket.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
