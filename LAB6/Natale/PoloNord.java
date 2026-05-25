package Natale;

public class PoloNord
{
	private int NE;
	private int NR;
	private int renneArrivate;
	private int elfiInDifficolta;

	public PoloNord(int numeroElfi, int numeroRenne)
	{
		NE=numeroElfi;
		NR=numeroRenne;
		renneArrivate=0;
		elfiInDifficolta=0;
	}

	public synchronized void chiediAiuto()
	{
		try
		{
			while(abbastanzaElfiInDifficolta()) wait();
			elfiInDifficolta++;
			System.out.println("Un elfo è stato aiutato");
			notifyAll();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public synchronized boolean abbastanzaElfiInDifficolta()
	{
		return elfiInDifficolta>=3;
	}

	public synchronized void rientraAlPolo()
	{
		try
		{
			renneArrivate++;
			System.out.println("è rientrata la "+renneArrivate+" renna");
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public synchronized boolean tutteRenneArrivate()
	{
		return renneArrivate>=NR;
	}
}
