package ProdConsServer;

import java.io.*;
import java.net.*;

public class ProducerClient
{
	public static void main(String[] args)
	{
		try
		{
			Socket socket=new Socket("localhost",4242);
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			pw.println("producer");
			pw.flush();
			String mess=br.readLine();
			System.out.println("Producer tried to connect, recieved: "+mess);
			if(mess.equals("okprod"))
			{
				System.out.println("Producer connected");
				pw.println("message");
				pw.flush();
				mess=br.readLine();
				if(mess.equals("okins")) System.out.println("Producer wrote message");
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
