package RWexclusive;
public class Reader implements Runnable
{
	RWbasic rw;
	Reader(RWexclusive _rw)
	{
		rw=_rw;
	}
	@Override
	public void run()
	{
		int data=rw.read();
		System.out.println(data);
	}
}
