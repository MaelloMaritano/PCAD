package RW;

public class Writer implements Runnable
{
	RWbasic rw;
	Writer(RW _rw)
	{
		rw=_rw;
	}
	@Override
	public void run()
	{
		rw.write();
	}
}
