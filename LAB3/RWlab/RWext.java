package RWlab;

public class RWext extends RWbasic
{
	private int currentReaders=0;
	private boolean writing=false;
	private boolean justWritten=false;

    public RWext() {}

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
		justWritten=false;
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
			while(writing || justWritten || currentReaders>0) wait();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		writing=true;
		super.write();
		justWritten=true;
		writing=false;
		notifyAll();
	}
}
