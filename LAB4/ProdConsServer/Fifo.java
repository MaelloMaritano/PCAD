package ProdConsServer;

import java.util.*;

public class Fifo
{
	private List<String> queue;

	public Fifo()
	{
		queue=new LinkedList<String>();
	}
	public synchronized String readLine()
	{
		try
		{
			while(queue.isEmpty()) {wait();}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return queue.removeFirst();
	}
	public synchronized void writeLine(String line)
	{
		try
		{
			queue.add(line);
			notifyAll();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
