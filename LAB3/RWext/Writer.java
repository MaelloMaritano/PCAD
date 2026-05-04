package RWext;

public class Writer implements Runnable
{
	RWbasic rw;
	Writer(RWext _rw)
	{
		rw=_rw;
	}
	@Override
	public void run()
	{
		rw.write();
	}
}
