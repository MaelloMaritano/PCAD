package Natale;

import java.util.concurrent.Semaphore;

public class StatoElfi
{
	private static final int MAX=3;

	private final int numeroElfi;
	private Semaphore elfiInDifficolta;
	private int elfiInCoda;
	private boolean aiuto;

	public StatoElfi(int _numeroElfi)
	{
		numeroElfi=_numeroElfi;
		elfiInCoda=0;
		elfiInDifficolta=new Semaphore(MAX);
		aiuto=false;
	}

	public synchronized void chiediAiuto(String nome)
	{
		try
		{
			elfiInDifficolta.acquire();
			elfiInCoda++;
			if(elfiInCoda>=MAX) notifyAll();	//sveglia Babbo Natale
			while(!aiuto) wait();
			System.out.println(nome+" viene aiutato da Babbo Natale");
			elfiInCoda--;
			notifyAll();
			while(aiuto) wait();
			System.out.println(nome+" torna al lavoro");
			elfiInDifficolta.release();
			notifyAll();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public synchronized void aiutaUnElfo()
	{
		try
		{
			aiuto=true;
			notifyAll();
			System.out.println("Babbo Natale sta aiutando gli elfi");
			while(elfiInCoda>0) wait();
			aiuto=false;
			notifyAll();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
