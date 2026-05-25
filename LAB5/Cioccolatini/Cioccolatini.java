package Cioccolatini;

public class Cioccolatini
{
	private static final int NM=3;	//numero mangiatori

	public static void main(String[] args)
	{
		Scatola scatola=new Scatola();

		Pasticciere pasticciere=new Pasticciere(scatola);
		Thread p=new Thread(pasticciere);
		p.start();

		Mangiatore[] mangiatori=new Mangiatore[NM];
		for (int i=0; i<NM; i++)
		{
			mangiatori[i]=new Mangiatore(scatola);
			Thread t=new Thread(mangiatori[i]);
			t.start();
		}
	}
}
