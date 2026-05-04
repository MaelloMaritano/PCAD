package RWlab;

public class RW extends RWbasic
{
	private int currentReaders=0;
	private boolean writing=false;

    public RW() {}

	private synchronized void startReading()
	{
		while(writing)
		{
			try { wait(); }
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
		}
		currentReaders++;
	}
	private synchronized void endReading()
	{
		currentReaders--;
		notifyAll();
	}

	@Override
	public int read()
	{
		startReading();
		int tmp=super.read();
		endReading();
		return tmp;
	}

	@Override
	public synchronized void write()
	{
		try
		{
			while(writing || currentReaders>0) wait();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		writing=true;
		super.write();
		writing=false;
		notifyAll();
	}
}
