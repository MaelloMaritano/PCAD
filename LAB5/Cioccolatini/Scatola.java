package Cioccolatini;

public class Scatola
{
	private static final int P=5;	//numero massimo cioccolatini
	int cioccolatini;

	public Scatola()
	{
		cioccolatini=0;
	}

	public synchronized void riempi()
	{
		try
		{
			while(cioccolatini!=0) wait();
			cioccolatini=P;
			System.out.println("Il pasticciere ha riempito la scatola");
			notifyAll();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public synchronized void mangia()
	{
		try
		{
			while(cioccolatini==0) wait();
			cioccolatini--;
			System.out.println("Un cioccolatino è stato mangiato, ne rimangono "+cioccolatini);
			if(cioccolatini==0) notify();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
