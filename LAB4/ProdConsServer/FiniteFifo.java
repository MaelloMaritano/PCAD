package ProdConsServer;

import java.util.*;

public class FiniteFifo extends Fifo
{
	private List<String> queue;
	private final int size;
	private static final int DEFAULT_SIZE=10;

	public FiniteFifo(int _size)
	{
		queue=new LinkedList<String>();
		if(_size>=0) size=_size;
		else size=DEFAULT_SIZE;
	}
	@Override
	public synchronized String readLine()
	{
		try
		{
			while(queue.isEmpty())
			{
				System.out.println("Lista vuota, aspetto...");
				wait();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		String mess=queue.removeFirst();
		System.out.println("Lista non vuota, leggo "+mess);
		notifyAll();
		return mess;
	}
	@Override
	public synchronized void writeLine(String line)
	{
		try
		{
			while(isFull())
			{
				System.out.println("Lista piena, aspetto...");
				wait();
			}
			System.out.println("Lista non piena, scrivo "+line);
			queue.add(line);
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public boolean isFull()
	{
		return queue.size()>=size;
	}
}
