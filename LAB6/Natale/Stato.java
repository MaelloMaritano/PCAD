package Natale;

import java.util.concurrent.Semaphore;

public class Stato
{
	// BABBO NATALE

	public synchronized void dorme()
	{
		try
		{
			while(!tutteRenneTornate() || !treElfiInCoda()) wait();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	// RENNE

	private final int numeroRenne;
	private int renneInCapanna;
	private boolean regaliDistribuiti;

	public synchronized boolean tutteRenneTornate()
	{
		return renneInCapanna>=numeroRenne;
	}

	public synchronized void tornaDaBabboNatale(String nomeRenna)
	{
		try
		{
			System.out.println(nomeRenna+" torna dalla vacanza");
			renneInCapanna++;
			if(tutteRenneTornate())
			{
				System.out.println("Tutte le renne sono tornate");
				notifyAll();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public synchronized void trainaSlitta(String nome)
	{
		try
		{
			while(!regaliDistribuiti) wait();
			renneInCapanna--;
			notifyAll();
			while(regaliDistribuiti) wait();	//aspetta che TUTTE le renne siano tornare in vacanza
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public synchronized void distribuisciRegali()
	{
		try
		{
			System.out.println("Babbo Natale e le renne distribuiscono i regali");
			regaliDistribuiti=true;
			System.out.println("Babbo Natale finisce di distribuire i regali");
			notifyAll();
			while(renneInCapanna>0) wait();
			regaliDistribuiti=false;
			notifyAll();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	//ELFI

	private static final int MAX=3;

	private final int numeroElfi;
	private Semaphore elfiInDifficolta;
	private int elfiInCoda;
	private boolean aiuto;

	public synchronized boolean treElfiInCoda()
	{
		return elfiInCoda>=MAX;
	}

	public synchronized void chiediAiuto(String nome)
	{
		try
		{
			elfiInDifficolta.acquire();
			System.out.println(nome+" è in coda");
			elfiInCoda++;
			if(treElfiInCoda()) notifyAll();	//sveglia Babbo Natale
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

	public synchronized void aiutaElfi()
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

	//COSTRUTTORE

	public Stato(int _numeroRenne, int _numeroElfi)
	{
		numeroRenne=_numeroRenne;
		renneInCapanna=0;
		regaliDistribuiti=false;

		numeroElfi=_numeroElfi;
		elfiInCoda=0;
		elfiInDifficolta=new Semaphore(MAX);
		aiuto=false;
	}
}
