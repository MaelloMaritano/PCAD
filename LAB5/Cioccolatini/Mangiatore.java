package Cioccolatini;

public class Mangiatore implements Runnable
{
	Scatola scatola;
	public Mangiatore(Scatola _scatola)
	{
		scatola=_scatola;
	}

	public void run()
	{
		while(true)
		{
			scatola.mangia();
		}
	}
}
