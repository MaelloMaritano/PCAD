package Natale;

public class StatoRenne
{
	private final int numeroRenne;
	private int renneInCapanna;
	private boolean regaliDistribuiti;

	public StatoRenne(int _numeroRenne)
	{
		numeroRenne=_numeroRenne;
		renneInCapanna=0;
		regaliDistribuiti=false;
	}

	public boolean tutteRenneTornate()
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

	public synchronized void aspettaRenne()
	{
		try
		{
			while(!tutteRenneTornate()) wait();
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
}
