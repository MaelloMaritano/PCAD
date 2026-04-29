package RWbasic;
public class Writer implements Runnable
{
	RWbasic rw;
	Writer(RWbasic _rw)
	{
		rw=_rw;
	}
	@Override
	public void run()
	{
		rw.write();
	}
}
