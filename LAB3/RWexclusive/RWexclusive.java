package RWexclusive;

public class RWexclusive extends RWbasic
{
    public RWexclusive() {}
	@Override
	public synchronized int read()
	{
		return super.read();
	}
	@Override
	public synchronized void write()
	{
		super.write();
	}
}
