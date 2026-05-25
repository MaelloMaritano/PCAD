package ProdConsServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Service implements Runnable
{
	public Socket socket;
	private Fifo buffer;

	public Service(Socket s, Fifo buf)
	{
		this.socket=s;
		this.buffer=buf;
	}

	public void run()
	{
		try
		{
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			String mess=br.readLine();
			//System.out.println("Receive message:"+mess);
			switch (mess)
			{
				case "producer":
					System.out.println("Producer connected");
					pw.println("okprod");
					pw.flush();
					buffer.writeLine(br.readLine());
					pw.println("okins");
					break;
				case "consumer":
					System.out.println("Consumer connected");
					pw.println("okcons");
					pw.flush();
					pw.println(buffer.readLine());
					break;
				default:
					throw new AssertionError();
			}
			pw.flush();
			br.close();
			pw.close();
			socket.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
