package RWexclusive;
public class Writer implements Runnable
{
	RWbasic rw;
	Writer(RWexclusive _rw)
	{
		rw=_rw;
	}
	@Override
	public void run()
	{
		rw.write();
	}
}
