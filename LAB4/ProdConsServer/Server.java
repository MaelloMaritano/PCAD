package ProdConsServer;

import java.net.*;

public class Server
{
	public static void main(String[] args)
	{
		try
		{
			ServerSocket server=new ServerSocket(4242);
			Fifo buffer=new FiniteFifo(10);
			while(true)
			{
				Socket socket=server.accept();
				Service serv=new Service(socket, buffer);
				Thread t=new Thread(serv);
				t.start();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
