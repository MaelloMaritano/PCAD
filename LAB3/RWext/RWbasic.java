package RWext;

public class RWbasic
{
	private int data=0;

    public RWbasic() {}
	public int read()
	{
		return data;
	}
	public void write()
	{
		int tmp=data;
		tmp++;
		try
		{
			java.lang.Thread.sleep(1);
		}
		catch(Exception e) {}
		data=tmp;
	}
}
