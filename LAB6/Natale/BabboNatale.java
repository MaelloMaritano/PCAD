package Natale;
public class BabboNatale implements Runnable
{
	private Stato stato;
	//private StatoElfi statoElfi;

    public BabboNatale(Stato _stato)
	{
		stato=_stato;
    }

	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println("Babbo Natale dorme");
				stato.dorme();
				if(stato.tutteRenneTornate()) stato.distribuisciRegali();
				if(stato.treElfiInCoda()) stato.aiutaElfi();
			}
			catch(Exception e)
			{
				System.out.println(e);
				e.printStackTrace();
			}
		}
	}
}
