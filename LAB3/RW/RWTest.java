package RW;

public class RWTest
{
	public static void main(String[] args)
	{
		try
		{
			Thread[] threads=new Thread[100];
			RW rw=new RW();
			Reader reader=new Reader(rw);
			Writer writer=new Writer(rw);

			for(int i=0; i<threads.length; i++)
			{
				if(i%2==0) threads[i]=new Thread(reader);
				else threads[i]=new Thread(writer);
			}
			for (Thread thread : threads) { thread.start(); }
			for (Thread thread : threads) { thread.join(); }

			System.out.println("END");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}	
}
