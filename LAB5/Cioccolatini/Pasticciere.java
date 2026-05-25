package Cioccolatini;

public class Pasticciere implements Runnable
{
	Scatola scatola;
	public Pasticciere(Scatola _scatola)
	{
		scatola=_scatola;
	}

	public void run()
	{
		while(true)
		{
			scatola.riempi();
		}
	}
}
