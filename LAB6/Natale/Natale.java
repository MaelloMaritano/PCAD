package Natale;

public class Natale
{
	private static final int NE=3;
	private static final int NR=9;

	public static void main(String[] args)
	{
		Thread bn=new Thread(new BabboNatale());
		bn.start();

		Elfo[] elfi=new Elfo[NE];
		for (int i=0; i<NE; i++)
		{
			elfi[i]=new Elfo();
			Thread t=new Thread(elfi[i]);
			t.start();
		}

		Renna[] renne=new Renna[NR];
		for (int i=0; i<NR; i++)
		{
			renne[i]=new Renna();
			Thread t=new Thread(renne[i]);
			t.start();
		}
	}
}